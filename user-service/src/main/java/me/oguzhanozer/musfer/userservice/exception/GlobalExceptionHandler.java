package me.oguzhanozer.musfer.userservice.exception;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponseException(HttpStatus.BAD_REQUEST);
        errorResponse.getBody().setTitle("Validation failed");
        errorResponse.getBody().setDetail("One or more invalid fields exist in request. See 'errors' for details");
        ArrayNode errors = JsonNodeFactory.instance.arrayNode();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.addObject()
                    .put("field", fieldError.getField())
                    .put("message", fieldError.getDefaultMessage())
                    .putPOJO("rejectedValue", fieldError.getRejectedValue());
        });
        errorResponse.getBody().setProperty("errors", errors);
        return errorResponse;
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ErrorResponse handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponseException(HttpStatus.CONFLICT);
        errorResponse.getBody().setTitle("Email already exists.");
        errorResponse.getBody().setDetail(ex.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleUserNotFound(UserNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponseException(HttpStatus.NOT_FOUND);
        errorResponse.getBody().setTitle("User not found.");
        errorResponse.getBody().setDetail(ex.getMessage());
        return errorResponse;
    }

}
