package com.example.library_borrowing_api.service;

import com.example.library_borrowing_api.dto.book.BookResponse;
import com.example.library_borrowing_api.dto.book.CreateBookRequest;

import java.util.List;

public interface BookService {
    BookResponse createBook(CreateBookRequest create);
    List<BookResponse> getAllBooks();
}
