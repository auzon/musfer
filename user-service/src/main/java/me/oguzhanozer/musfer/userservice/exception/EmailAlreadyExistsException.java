package me.oguzhanozer.musfer.userservice.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("Email: %s already exists.".formatted(email));
    }
}
