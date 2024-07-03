package com.example.aboutme._core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String formatDate(LocalDateTime date) {
        return date.format(DATE_FORMATTER);
    }

    public static String formatTimestamp(Timestamp timestamp) {
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        return localDateTime.format(TIMESTAMP_FORMATTER);
    }

    public static String number(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###원");
        return decimalFormat.format(number);
    }

    public static int parseNumber(String numberStr) {
        try {
            return Integer.parseInt(numberStr.replace(",", ""));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format: " + numberStr, e);
        }
    }

    public static double parseDouble(String numberStr) {
        try {
            return Double.parseDouble(numberStr.replace(",", ""));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format: " + numberStr, e);
        }
    }

    public static double roundToOneDecimalPlace(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

