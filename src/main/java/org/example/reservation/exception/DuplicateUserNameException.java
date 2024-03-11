package org.example.reservation.exception;

public class DuplicateUserNameException extends RuntimeException {
    public DuplicateUserNameException() {
        super("IDが重複しています");
    }

    public DuplicateUserNameException(String message) {
        super(message);
    }
}
