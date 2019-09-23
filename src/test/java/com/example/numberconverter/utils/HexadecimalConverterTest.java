package com.example.numberconverter.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class HexadecimalConverterTest {
    @ParameterizedTest
    @MethodSource("parametersForShouldConvertNumber")
    void shouldConvertNumber(int number, String expectedResult) {
        // when
        String result = HexadecimalConverter.convert(number);

        // then
        assertEquals(expectedResult, result);
    }

    static Stream<Arguments> parametersForShouldConvertNumber() {
        return Stream.of(
                arguments(-10, "-a"),
                arguments(-0, "0"),
                arguments(100, "64"),
                arguments(Integer.MAX_VALUE, "7fffffff"),
                arguments(Integer.MIN_VALUE, "-80000000")
        );
    }
}
