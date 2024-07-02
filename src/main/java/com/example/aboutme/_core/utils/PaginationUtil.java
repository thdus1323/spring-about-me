package com.example.aboutme._core.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PaginationUtil {
    public static List<Integer> getPageNumbers(int currentPage, int totalPages, int maxPageDisplay) {
        int startPage = Math.max(0, (currentPage / maxPageDisplay) * maxPageDisplay);
        int endPage = Math.min(startPage + maxPageDisplay - 1, totalPages - 1);
        return IntStream.rangeClosed(startPage, endPage)
                .boxed()
                .collect(Collectors.toList());
    }
}
