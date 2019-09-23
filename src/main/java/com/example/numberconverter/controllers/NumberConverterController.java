package com.example.numberconverter.controllers;

import com.example.numberconverter.enums.ConversionType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import static com.example.numberconverter.enums.ErrorMessage.NOT_SUPPORTED_CONVERSATION_TYPE;

@RestController
public class NumberConverterController {

    @GetMapping("/convertNumber")
    public ResponseEntity<String> convertNumber(@RequestParam int number, @RequestParam ConversionType conversionType) {
        if (conversionType == null) {
            throw new IllegalArgumentException(NOT_SUPPORTED_CONVERSATION_TYPE.formatMessage(Arrays.toString(ConversionType.values())));
        }

        return ResponseEntity.ok(conversionType.convert(number));
    }

}
