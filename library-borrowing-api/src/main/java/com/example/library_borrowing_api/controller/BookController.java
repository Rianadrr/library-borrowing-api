package com.example.library_borrowing_api.controller;

import com.example.library_borrowing_api.dto.book.BookResponse;
import com.example.library_borrowing_api.dto.book.CreateBookRequest;
import com.example.library_borrowing_api.entity.BookEntity;
import com.example.library_borrowing_api.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {
    public final BookRepository bookRepository;

    public BookController (BookRepository bookRepository){

        this.bookRepository = bookRepository;

    }
    @PostMapping
    public ResponseEntity <BookResponse> createBook (@Valid @RequestBody CreateBookRequest create) {
        BookEntity book = BookEntity.builder()
                .bookName(create.getBookName())
                .isbn(create.getIsbn())
                .build();
        bookRepository.save(book);

        BookResponse response = BookResponse.builder()
                .id(book.getId())
                .bookName(book.getBookName())
                .isbn(book.getIsbn())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllMembers() {
        List<BookEntity> books = bookRepository.findAll();

        List<BookResponse> responseAll = books.stream()
                .map(book -> BookResponse.builder()
                        .id(book.getId())
                        .bookName(book.getBookName())
                        .isbn(book.getIsbn())
                        .build()
                )
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseAll);
    }
}
