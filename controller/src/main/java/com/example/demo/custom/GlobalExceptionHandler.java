package com.example.demo.custom;

import com.example.demo.dto.error.ErrorDTO;
import com.example.demo.exceptions.GeneralException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ErrorDTO> generateNotFoundException(GeneralException ex) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .message(ex.getMessage())
                .status(String.valueOf(ex.getStatus().value()))
                .code(ex.getCode())
                .build();

        return new ResponseEntity<>(errorDTO, ex.getStatus());
    }
}