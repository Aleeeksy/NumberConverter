package com.example.numberconverter.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    NOT_VALID_NUMBER("Passed value is not a valid number"),
    NOT_SUPPORTED_CONVERSATION_TYPE("Passed conversion type is not supported. Allowed conversion types: %s"),
    NOT_SUPPORTED_VALUE_FOR_ROMAN("Roman numerals system do not support negative values or zero");

    private final String message;

    public String formatMessage(String... args) {
        return String.format(this.message, args);
    }
}
