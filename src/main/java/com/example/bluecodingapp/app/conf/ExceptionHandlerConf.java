package com.example.bluecodingapp.app.conf;

import com.example.bluecodingapp.app.dto.exception.ExceptionDTO;
import com.example.bluecodingapp.domain.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ExceptionHandlerConf {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleUserNotFoundException(UserNotFoundException ex) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage());
        return ResponseEntity.status(404).body(exceptionDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
       ExceptionDTO exceptionDTO = new ExceptionDTO(Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage());
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
       ExceptionDTO exceptionDTO = new ExceptionDTO(ex.getMessage());
        return ResponseEntity.badRequest().body(exceptionDTO);
    }
}
