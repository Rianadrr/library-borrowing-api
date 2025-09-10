package com.example.library_borrowing_api.controller;

import com.example.library_borrowing_api.dto.borrowing.BorrowingResponse;
import com.example.library_borrowing_api.dto.borrowing.CreateBorrowingRequest;
import com.example.library_borrowing_api.entity.BookEntity;
import com.example.library_borrowing_api.entity.BorrowingEntity;
import com.example.library_borrowing_api.entity.MemberEntity;
import com.example.library_borrowing_api.repository.BookRepository;
import com.example.library_borrowing_api.repository.BorrowingRepository;
import com.example.library_borrowing_api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/borrowings")
public class BorrowingController {

    private final BorrowingRepository borrowingRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<BorrowingResponse> borrowBook(@RequestBody CreateBorrowingRequest request) {
        MemberEntity member = memberRepository.findById(request.getMemberId())
                .orElse(null);

        if (member == null) {
            return ResponseEntity.status(404).body(null);
        }

        BookEntity book = bookRepository.findById(request.getBookId())
                .orElse(null);

        if (book == null) {
            return ResponseEntity.status(404).body(null);
        }

        if (member.getBorrowing().size() >= 5) {
            throw new RuntimeException("Member has already borrowed 5 books");
        }

        BorrowingEntity borrowing = BorrowingEntity.builder()
                .book(book)
                .member(member)
                .borrowDate(LocalDate.now())
                .status("BORROWED")
                .build();

        borrowingRepository.save(borrowing);

        return ResponseEntity.ok(BorrowingResponse.builder()
                .id(borrowing.getId())
                .bookId (book.getId())
                .memberId(member.getId())
                .bookName(book.getBookName())
                .isbn(book.getIsbn())
                .memberName(member.getMemberName())
                .borrowDate(borrowing.getBorrowDate())
                .status(borrowing.getStatus())
                .build());
    }

    @GetMapping
    public ResponseEntity<List<BorrowingResponse>> getAllBorrowings() {
        List<BorrowingResponse> response = borrowingRepository.findAll().stream()
                .map(borrow -> BorrowingResponse.builder()
                        .id(borrow.getId())
                        .bookId(borrow.getBook().getId())
                        .memberId(borrow.getMember().getId())
                        .bookName(borrow.getBook().getBookName())
                        .isbn(borrow.getBook().getIsbn())
                        .memberName(borrow.getMember().getMemberName())
                        .borrowDate(borrow.getBorrowDate())
                        .returnDate(borrow.getReturnDate())
                        .status(borrow.getStatus())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingResponse> getBorrowingById(@PathVariable Long id) {
        BorrowingEntity borrow = borrowingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrowing not found"));

        return ResponseEntity.ok(BorrowingResponse.builder()
                .id(borrow.getId())
                .bookId(borrow.getBook().getId())
                .memberId(borrow.getMember().getId())
                .bookName(borrow.getBook().getBookName())
                .isbn(borrow.getBook().getIsbn())
                .memberName(borrow.getMember().getMemberName())
                .borrowDate(borrow.getBorrowDate())
                .returnDate(borrow.getReturnDate())
                .status(borrow.getStatus())
                .build());
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<BorrowingResponse> returnBook(@PathVariable Long id) {
        BorrowingEntity borrow = borrowingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrowing not found"));

        borrow.setReturnDate(LocalDate.now());
        borrow.setStatus("RETURNED");
        borrowingRepository.save(borrow);

        return ResponseEntity.ok(BorrowingResponse.builder()
                .id(borrow.getId())
                .bookId(borrow.getBook().getId())
                .memberId(borrow.getMember().getId())
                .bookName(borrow.getBook().getBookName())
                .isbn(borrow.getBook().getIsbn())
                .memberName(borrow.getMember().getMemberName())
                .borrowDate(borrow.getBorrowDate())
                .returnDate(borrow.getReturnDate())
                .status(borrow.getStatus())
                .build());
    }
}
