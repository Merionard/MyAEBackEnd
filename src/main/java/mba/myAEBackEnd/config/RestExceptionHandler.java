package mba.myAEBackEnd.config;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {
    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handleException(
            RuntimeException ex, WebRequest request) {
        log.error(ex.getMessage());
        String bodyOfResponse = "Application error";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(bodyOfResponse);
    }
}
