package payment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum payType {
    CARD("카드"), VIRTUAL_ACCOUNT("가상계좌");

    private final String name;
}
