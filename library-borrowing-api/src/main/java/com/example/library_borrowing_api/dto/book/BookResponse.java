package com.example.library_borrowing_api.dto.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BookResponse {
    private Long id;
    private String isbn;
    private String bookName;
}
