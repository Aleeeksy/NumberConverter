package com.example.numberconverter.controllers;

import com.example.numberconverter.enums.ConversionType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static com.example.numberconverter.enums.ErrorMessage.NOT_SUPPORTED_CONVERSATION_TYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class NumberConverterControllerTest {
    private static final int NUMBER_TO_CONVERT = 99;
    private static final String NUMBER_AFTER_CONVERSION = "99";

    private final NumberConverterController numberConverterController = new NumberConverterController();

    @Test
    void shouldThrowIllegalArgumentException() {
        // when
        Throwable thrown = catchThrowable(() -> numberConverterController.convertNumber(NUMBER_TO_CONVERT, null));

        // then
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_SUPPORTED_CONVERSATION_TYPE.formatMessage(Arrays.toString(ConversionType.values())));
    }

    @Test
    void shouldConvertNumber() {
        // given
        ConversionType conversionTypeMock = mock(ConversionType.class);
        when(conversionTypeMock.convert(NUMBER_TO_CONVERT)).thenReturn(NUMBER_AFTER_CONVERSION);

        // when
        ResponseEntity<String> response = numberConverterController.convertNumber(NUMBER_TO_CONVERT,
                conversionTypeMock);

        // then
        verify(conversionTypeMock).convert(NUMBER_TO_CONVERT);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(NUMBER_AFTER_CONVERSION, response.getBody());

    }
}