package com.example.numberconverter.utils;

import java.util.TreeMap;

import static com.example.numberconverter.enums.ErrorMessage.NOT_SUPPORTED_VALUE_FOR_ROMAN;

public class RomanConverter {

    private final static TreeMap<Integer, String> ARABIC_ROMAN = new TreeMap<>();

    static {
        ARABIC_ROMAN.put(1, "I");
        ARABIC_ROMAN.put(4, "IV");
        ARABIC_ROMAN.put(5, "V");
        ARABIC_ROMAN.put(9, "IX");
        ARABIC_ROMAN.put(10, "X");
        ARABIC_ROMAN.put(40, "XL");
        ARABIC_ROMAN.put(50, "L");
        ARABIC_ROMAN.put(90, "XC");
        ARABIC_ROMAN.put(100, "C");
        ARABIC_ROMAN.put(400, "CD");
        ARABIC_ROMAN.put(500, "D");
        ARABIC_ROMAN.put(900, "CM");
        ARABIC_ROMAN.put(1000, "M");
    }

    public static String convert(int number) {
        validate(number);
        return toRoman(number);
    }

    private static void validate(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException(NOT_SUPPORTED_VALUE_FOR_ROMAN.getMessage());
        }
    }

    private static String toRoman(int number) {
        int nearestSmallerNumber = ARABIC_ROMAN.floorKey(number);
        if (number == nearestSmallerNumber) {
            return ARABIC_ROMAN.get(number);
        }
        return ARABIC_ROMAN.get(nearestSmallerNumber) + convert(number - nearestSmallerNumber);
    }
}
