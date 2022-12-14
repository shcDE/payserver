package payment.dto;

import lombok.Data;

@Data
public class PaymentResHandleCardDto {
    String company;                     // "현대",
    String number;                      // "433012******1234",
    String installmentPlanMonths;       // 0,
    String isInterestFree;              // false,
    String approveNo;                   // "00000000",
    String useCardPoint;                // false,
    String cardType;                    // "신용",
    String ownerType;                   // "개인",
    String acquireStatus;               // "READY",
    String receiptUrl;
}
