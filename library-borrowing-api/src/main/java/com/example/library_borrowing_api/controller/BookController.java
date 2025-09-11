package com.example.library_borrowing_api.controller;

import com.example.library_borrowing_api.dto.book.BookResponse;
import com.example.library_borrowing_api.dto.book.CreateBookRequest;
import com.example.library_borrowing_api.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody CreateBookRequest create) {
        return ResponseEntity.ok(bookService.createBook(create));
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
}
