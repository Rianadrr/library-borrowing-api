package com.example.library_borrowing_api.service.impl;

import com.example.library_borrowing_api.dto.book.BookResponse;
import com.example.library_borrowing_api.dto.book.CreateBookRequest;
import com.example.library_borrowing_api.entity.BookEntity;
import com.example.library_borrowing_api.repository.BookRepository;
import com.example.library_borrowing_api.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public BookResponse createBook(CreateBookRequest create) {
        BookEntity book = BookEntity.builder()
                .bookName(create.getBookName())
                .isbn(create.getIsbn())
                .build();

        bookRepository.save(book);

        return BookResponse.builder()
                .id(book.getId())
                .bookName(book.getBookName())
                .isbn(book.getIsbn())
                .build();
    }

    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book -> BookResponse.builder()
                        .id(book.getId())
                        .bookName(book.getBookName())
                        .isbn(book.getIsbn())
                        .build())
                .collect(Collectors.toList());
    }
}
