package jm.task.core.jdbc.exception;

public class DatabaseCloseException extends RuntimeException {
    public DatabaseCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}