package jm.task.core.jdbc.exception;

public class DropTableException extends RuntimeException {
    public DropTableException(String message, Throwable cause) {
        super(message, cause);
    }
}