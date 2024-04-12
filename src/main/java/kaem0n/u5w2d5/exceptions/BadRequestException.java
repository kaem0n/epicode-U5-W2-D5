package kaem0n.u5w2d5.exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;

public class BadRequestException extends RuntimeException {
    private List<ObjectError> errorList;

    public BadRequestException(String msg) {
        super(msg);
    }

    public BadRequestException(List<ObjectError> errorList) {
        super("Validation errors on payload.");
        this.errorList = errorList;
    }
}
