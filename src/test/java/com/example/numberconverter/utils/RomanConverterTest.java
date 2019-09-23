package com.example.numberconverter.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.example.numberconverter.enums.ErrorMessage.NOT_SUPPORTED_VALUE_FOR_ROMAN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class RomanConverterTest {

    @ParameterizedTest
    @MethodSource("parametersForShouldConvertNumber")
    void shouldConvertNumber(int number, String expectedResult) {
        // when
        String result = RomanConverter.convert(number);

        // then
        assertEquals(expectedResult, result);
    }

    static Stream<Arguments> parametersForShouldConvertNumber() {
        return Stream.of(
                arguments(1000, "M"),
                arguments(900, "CM"),
                arguments(500, "D"),
                arguments(400, "CD"),
                arguments(100, "C"),
                arguments(90, "XC"),
                arguments(50, "L"),
                arguments(40, "XL"),
                arguments(10, "X"),
                arguments(9, "IX"),
                arguments(5, "V"),
                arguments(4, "IV"),
                arguments(1, "I"),
                arguments(85, "LXXXV"),
                arguments(9999, "MMMMMMMMMCMXCIX")
        );
    }


    @ParameterizedTest
    @MethodSource("parametersForShouldThrowIllegalArgumentException")
    void shouldThrowIllegalArgumentException(int number) {
        // when
        Throwable thrown = catchThrowable(() -> RomanConverter.convert(number));

        // then
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_SUPPORTED_VALUE_FOR_ROMAN.getMessage());
    }

    static Stream<Integer> parametersForShouldThrowIllegalArgumentException() {
        return Stream.of(0, -9, Integer.MIN_VALUE);
    }

}