package payment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import payment.advice.ExMessage;
import payment.dto.OrderNameType;
import payment.dto.PaymentReq;
import payment.dto.PaymentRes;
import payment.dto.PaymentResHandleFailDto;
import payment.entity.Payment;
import payment.exception.BussinessException;
import payment.repository.MemberRepository;
import payment.repository.PaymentRepository;
import org.springframework.http.HttpHeaders;
import javax.transaction.Transactional;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final MemberRepository memberRepository;

    @Value("${payments.toss.test_client_api_key}")
    private String testClientApiKey;

    @Value("${payments.toss.test_secret_api_key}")
    private String testSecretApiKey;

    @Value("${payments.toss.success_url}")
    private String successCallBackUrl;

    @Value("${payments.toss.fail_url}")
    private String failCallBackUrl;
    @Transactional
    public PaymentRes requestPayments(PaymentReq paymentReq) {
        Long amount = paymentReq.getAmount();
        String payType = paymentReq.getPayType().name();
        String customerEmail = paymentReq.getCustomerEmail();
        String orderName = paymentReq.getOrderName().name();

        if (amount == null || amount != 3000) {
            throw new BussinessException(ExMessage.PAYMENT_ERROR_ORDER_PRICE);
        }

        if (!payType.equals("CARD") && !payType.equals("카드")) {
            throw new BussinessException(ExMessage.PAYMENT_ERROR_ORDER_PAY_TYPE);
        }

        if (!orderName.equals(OrderNameType.STYLE_FEEDBACK.name()) &&
                !orderName.equals(OrderNameType.CRDI_OR_PRODUCT_RECMD.name())) {
            throw new BussinessException(ExMessage.PAYMENT_ERROR_ORDER_NAME);
        }

        PaymentRes paymentRes;
        try {
            Payment payment = paymentReq.toEntity();
            memberRepository.findByEmailFJ(customerEmail)
                    .ifPresentOrElse(
                            M -> M.addPayment(payment)
                            , () -> {
                                throw new BussinessException(ExMessage.MEMBER_ERROR_NOT_FOUND);
                            }
                    );
            paymentRes = payment.toDto();
            paymentRes.setSuccessUrl(successCallBackUrl);
            paymentRes.setFailUrl(failCallBackUrl);
            return paymentRes;
        } catch (Exception e) {
            throw new BussinessException(ExMessage.DB_ERROR_SAVE);
        }
    }

    @Transactional
    public void verifyRequest(String paymentKey, String orderId, Long amount) {
        paymentRepository.findByOrderId(orderId)
                .ifPresentOrElse(
                        P -> {
                            // 가격 비교
                            if (P.getAmount().equals(amount)) {
                                P.setPaymentKey(paymentKey);
                            } else {
                                throw new BussinessException(ExMessage.PAYMENT_ERROR_ORDER_AMOUNT);
                            }
                        }, () -> {
                            throw new BussinessException(ExMessage.UNDEFINED_ERROR);
                        }
                );
    }

    @Transactional
    public String requestFinalPayment(String paymentKey, String orderId, Long amount) {
        RestTemplate rest = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        testSecretApiKey = testSecretApiKey + ":";
        String encodedAuth = new String(Base64.getEncoder().encode(testSecretApiKey.getBytes(StandardCharsets.UTF_8)));
        headers.setBasicAuth(encodedAuth);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        JSONObject param = new JSONObject();
        param.put("orderId", orderId);
        param.put("amount", amount);

        return rest.postForEntity(
                "http://localhost:8080/" + paymentKey,
                new HttpEntity<>(param, headers),
                String.class
        ).getBody();
    }



    @Transactional
    public PaymentResHandleFailDto requestFail(String errorCode, String errorMsg, String orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new BussinessException(ExMessage.PAYMENT_ERROR_ORDER_NOTFOUND));
        payment.setPaySuccessYn("N");
        payment.setPayFailReason(errorMsg);

        return PaymentResHandleFailDto
                .builder()
                .orderId(orderId)
                .errorCode(errorCode)
                .errorMsg(errorMsg)
                .build();
    }

    @Transactional
    public String requestPaymentCancel(String paymentKey, String cancelReason) {
        RestTemplate rest = new RestTemplate();

        URI uri = URI.create("http://localhost:8080/" + paymentKey + "/cancel");

        HttpHeaders headers = new HttpHeaders();
        byte[] secretKeyByte = (testSecretApiKey + ":").getBytes(StandardCharsets.UTF_8);
        headers.setBasicAuth(new String(Base64.getEncoder().encode(secretKeyByte)));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        JSONObject param = new JSONObject();
        param.put("cancelReason", cancelReason);

        return rest.postForObject(
                uri,
                new HttpEntity<>(param, headers),
                String.class
        );
    }
}
