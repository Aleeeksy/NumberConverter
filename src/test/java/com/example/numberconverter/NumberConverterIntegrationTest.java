package com.example.numberconverter;

import com.example.numberconverter.enums.ConversionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static com.example.numberconverter.enums.ErrorMessage.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class NumberConverterIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @ValueSource(strings = {"", "ABC", "1.2", "1A"})
    void shouldHandleWrongNumberFormat(String number) throws Exception {
        mockMvc.perform(get("/convertNumber").param("number", number))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(NOT_VALID_NUMBER.getMessage()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "ABC", "HEX"})
    void shouldHandleWrongConversionType(String type) throws Exception {
        mockMvc.perform(get("/convertNumber").param("number", "100").param("conversionType", type))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(NOT_SUPPORTED_CONVERSATION_TYPE.formatMessage(Arrays.toString(ConversionType.values()))));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1"})
    void shouldHandleIllegalArgumentException(String number) throws Exception {
        mockMvc.perform(get("/convertNumber").param("number", number).param("conversionType", "ROMAN"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(NOT_SUPPORTED_VALUE_FOR_ROMAN.getMessage()));
    }

    @Test
    void shouldConvertArabicNumberToRomanNumber() throws Exception {
        mockMvc.perform(get("/convertNumber").param("number", "100").param("conversionType", "ROMAN"))
                .andExpect(status().isOk())
                .andExpect(content().string("C"));
    }

    @Test
    void shouldConvertArabicNumberToHexadecimalNumber() throws Exception {
        mockMvc.perform(get("/convertNumber").param("number", "100").param("conversionType", "HEXADECIMAL"))
                .andExpect(status().isOk())
                .andExpect(content().string("64"));
    }
}