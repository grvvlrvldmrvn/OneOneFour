package jm.task.core.jdbc.exception;

public class SaveUserException extends RuntimeException {
    public SaveUserException(String message, Throwable cause) {
        super(message, cause);
    }
}