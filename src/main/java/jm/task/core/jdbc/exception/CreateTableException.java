package jm.task.core.jdbc.exception;

public class CreateTableException extends RuntimeException {
    public CreateTableException(String message, Throwable cause) {
        super(message, cause);
    }
}