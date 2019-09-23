package com.example.numberconverter.enums;

import com.example.numberconverter.utils.HexadecimalConverter;
import com.example.numberconverter.utils.RomanConverter;

public enum ConversionType {
    HEXADECIMAL {
        @Override
        public String convert(int number) {
            return HexadecimalConverter.convert(number);
        }
    },
    ROMAN {
        @Override
        public String convert(int number) {
            return RomanConverter.convert(number);
        }
    };

    public abstract String convert(int number);
}
