package com.example.aboutme._core.utils;

import org.springframework.stereotype.Component;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component // @Controller 대신 @Component 사용
public class Formatter {

    public String number(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###원");
        return decimalFormat.format(number);
    }

    public int parseNumber(String numberStr) {
        try {
            return Integer.parseInt(numberStr.replace(",", ""));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format: " + numberStr, e);
        }
    }

    public double parseDouble(String numberStr) {
        try {
            return Double.parseDouble(numberStr.replace(",", ""));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format: " + numberStr, e);
        }
    }


    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String formatDate(LocalDateTime date) {
        return date.format(DATE_FORMATTER);
    }
}

