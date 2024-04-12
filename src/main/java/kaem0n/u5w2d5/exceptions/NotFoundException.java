package kaem0n.u5w2d5.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("Element with ID '" + id + "' not found.");
    }
}
