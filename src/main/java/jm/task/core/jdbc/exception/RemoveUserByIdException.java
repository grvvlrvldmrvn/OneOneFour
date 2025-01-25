package jm.task.core.jdbc.exception;

public class RemoveUserByIdException extends RuntimeException {
    public RemoveUserByIdException(String message, Throwable cause) {
        super(message, cause);
    }
}