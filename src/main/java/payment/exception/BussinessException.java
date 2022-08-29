package payment.exception;

import payment.advice.ExMessage;

public class BussinessException extends RuntimeException {

    public BussinessException(ExMessage exMessage) {
        super(exMessage.getMessage());
    }

    public BussinessException(String message) {
        super(message);
    }
}
