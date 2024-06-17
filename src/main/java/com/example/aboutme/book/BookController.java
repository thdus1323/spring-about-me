package com.example.aboutme.book;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class BookController {
    private final BookService bookService;
    private final HttpSession session;
}
