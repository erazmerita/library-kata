package ra.eu.library_kata.expection;

import ra.eu.library_kata.constants.ResponseCode;

public class CustomRuntimeException extends RuntimeException {

    public CustomRuntimeException(ResponseCode code) {
        super(code.name());
    }

    public CustomRuntimeException(String message) {
        super(message);
    }
}
