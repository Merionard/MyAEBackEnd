package mba.myAEBackEnd.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleException(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Application error";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(bodyOfResponse);
    }
}
