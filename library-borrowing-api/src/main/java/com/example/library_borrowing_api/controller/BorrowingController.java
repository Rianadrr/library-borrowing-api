package com.example.library_borrowing_api.controller;

import com.example.library_borrowing_api.dto.borrowing.BorrowingResponse;
import com.example.library_borrowing_api.dto.borrowing.CreateBorrowingRequest;
import com.example.library_borrowing_api.service.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/borrowings")
public class BorrowingController {

    private final BorrowingService borrowingService;

    @PostMapping
    public ResponseEntity<BorrowingResponse> borrowBook(@RequestBody CreateBorrowingRequest request) {
        return ResponseEntity.ok(borrowingService.borrowBook(request));
    }

    @GetMapping
    public ResponseEntity<List<BorrowingResponse>> getAllBorrowings() {
        return ResponseEntity.ok(borrowingService.getAllBorrowings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingResponse> getBorrowingById(@PathVariable Long id) {
        return ResponseEntity.ok(borrowingService.getBorrowingById(id));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<BorrowingResponse> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(borrowingService.returnBook(id));
    }
}
