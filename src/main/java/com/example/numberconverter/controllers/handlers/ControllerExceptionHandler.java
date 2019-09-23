package com.example.numberconverter.controllers.handlers;

import com.example.numberconverter.enums.ConversionType;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

import static com.example.numberconverter.enums.ErrorMessage.NOT_SUPPORTED_CONVERSATION_TYPE;
import static com.example.numberconverter.enums.ErrorMessage.NOT_VALID_NUMBER;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleWrongNumberFormat() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(NOT_VALID_NUMBER.getMessage());
    }

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<String> handleWrongConversionType() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(NOT_SUPPORTED_CONVERSATION_TYPE.formatMessage(Arrays.toString(ConversionType.values())));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
