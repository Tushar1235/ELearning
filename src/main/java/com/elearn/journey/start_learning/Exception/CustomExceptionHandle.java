package com.elearn.journey.start_learning.Exception;

import com.elearn.journey.start_learning.Exception.Util.CustomMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandle {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomMessages> handleresourseNotFound(
            ResourceNotFoundException resourceNotFoundException) {
        CustomMessages messages = new CustomMessages();
        messages.setMessage(resourceNotFoundException.getMessage());
        messages.setSuccess(false);
        return new ResponseEntity<>(messages, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArguemntException(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error->
                errors.put(((FieldError) error).getField(),error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }



}
