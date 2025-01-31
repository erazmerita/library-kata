package ra.eu.library_kata.expection;

import ra.eu.library_kata.constants.ResponseCode;

public class Custom400RuntimeException extends CustomRuntimeException {

    public Custom400RuntimeException(ResponseCode code) {
        super(code);
    }

    public Custom400RuntimeException(String message) {
        super(message);
    }
}
