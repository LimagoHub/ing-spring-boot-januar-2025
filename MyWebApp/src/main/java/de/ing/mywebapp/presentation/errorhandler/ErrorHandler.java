package de.ing.mywebapp.presentation.errorhandler;


import de.ing.mywebapp.SeltsamException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {



    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatusCode status, final WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField() + ":" + x.getDefaultMessage())
                .collect(Collectors.toList());
        body.put("errors", errors);

        // WICHTIG !!!!!!
        logger.error("Upps", ex);
        return  ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(SeltsamException.class)
    public ResponseEntity<Object> handlePersonenServiceException(SeltsamException ex, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        return  ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handlePersonenServiceException(Exception ex, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        return  ResponseEntity.badRequest().body(body);
    }
}
