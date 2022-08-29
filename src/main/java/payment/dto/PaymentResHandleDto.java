package payment.dto;

import lombok.Data;
import payment.entity.CancelPayment;

@Data
public class PaymentResHandleDto {
    String mId;                     // : "tosspayments", 가맹점 ID
    String version;                 // : "1.3", Payment 객체 응답 버전
    String paymentKey;
    String orderId;
    String orderName;               // : "토스 머그컵 외 2건",
    String currency;                // : "KRW",
    String method;                  // : "카드", 결제수단
    String totalAmount;
    String balanceAmount;
    String suppliedAmount;
    String vat;
    String status;                  // : "DONE", 결제 처리 상태
    String requestedAt;
    String approvedAt;
    String useEscrow;               // : false,
    String cultureExpense;          // : false,
    PaymentResHandleCardDto card;	// : 카드 결제,
    PaymentResHandleCancelDto[] cancels;	// : 결제 취소 이력 관련 객체
    String type;                    // : "NORMAL",	결제 타입 정보 (NOMAL, BILLING, CONNECTPAY)


    public CancelPayment toCancelPayment() {
        return CancelPayment.builder()
                .orderId(orderId)
                .orderName(orderName)
                .paymentKey(paymentKey)
                .requestedAt(requestedAt)
                .approvedAt(approvedAt)
                .cardCompany(card.getCompany())
                .cardNumber(card.getNumber())
                .cardReceiptUrl(card.getReceiptUrl())
                .cancelAmount(cancels[0].getCancelAmount())
                .cancelDate(cancels[0].getCanceledAt())
                .cancelReason(cancels[0].getCancelReason())
                .build();
    }
}
