package payment.entity;

import lombok.*;
import payment.dto.OrderNameType;
import payment.dto.PaymentRes;
import payment.dto.payType;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq", nullable = false, unique = true)
    private Long seq;

    @Column(nullable = false)
    private payment.dto.payType payType;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private OrderNameType orderName;

    @Column(nullable = false)
    private String customerEmail;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String createDate;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Member customer;

    @Setter
    @Column(nullable = false)
    private String paySuccessYn;			// 결제 성공 여부

    @Setter
    @Column
    private String paymentKey;

    @Setter
    @Column
    private String payFailReason;			// 결제 실패 이유

    public PaymentRes toDto() {
        return PaymentRes.builder()
                .payType(payType.name())
                .amount(amount)
                .orderId(orderId)
                .orderName(orderName.name())
                .customerEmail(customerEmail)
                .customerName(customerName)
                .createDate(createDate)
                .build();
    }
}
