package ra.eu.library_kata.expection;

import ra.eu.library_kata.constants.ResponseCode;

public class Custom404RuntimeException extends CustomRuntimeException {

    public Custom404RuntimeException(ResponseCode code) {
        super(code);
    }

    public Custom404RuntimeException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with the given input data %s: %s", resourceName, fieldName, fieldValue));
    }
}
