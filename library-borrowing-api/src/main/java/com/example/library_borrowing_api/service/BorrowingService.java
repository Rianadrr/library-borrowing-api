package com.example.library_borrowing_api.service;

import com.example.library_borrowing_api.dto.borrowing.BorrowingResponse;
import com.example.library_borrowing_api.dto.borrowing.CreateBorrowingRequest;

import java.util.List;

public interface BorrowingService {
    BorrowingResponse borrowBook(CreateBorrowingRequest request);
    List<BorrowingResponse> getAllBorrowings();
    BorrowingResponse getBorrowingById(Long id);
    BorrowingResponse returnBook(Long id);
}
