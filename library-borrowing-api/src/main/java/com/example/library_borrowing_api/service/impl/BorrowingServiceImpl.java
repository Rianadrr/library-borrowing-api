package com.example.library_borrowing_api.service.impl;

import com.example.library_borrowing_api.dto.borrowing.BorrowingResponse;
import com.example.library_borrowing_api.dto.borrowing.CreateBorrowingRequest;
import com.example.library_borrowing_api.entity.BookEntity;
import com.example.library_borrowing_api.entity.BorrowingEntity;
import com.example.library_borrowing_api.entity.MemberEntity;
import com.example.library_borrowing_api.repository.BookRepository;
import com.example.library_borrowing_api.repository.BorrowingRepository;
import com.example.library_borrowing_api.repository.MemberRepository;
import com.example.library_borrowing_api.service.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    @Override
    public BorrowingResponse borrowBook(CreateBorrowingRequest request) {
        MemberEntity member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        BookEntity book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

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

        return mapToResponse(borrowing);
    }

    @Override
    public List<BorrowingResponse> getAllBorrowings() {
        return borrowingRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BorrowingResponse getBorrowingById(Long id) {
        BorrowingEntity borrow = borrowingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrowing not found"));
        return mapToResponse(borrow);
    }

    @Override
    public BorrowingResponse returnBook(Long id) {
        BorrowingEntity borrow = borrowingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrowing not found"));

        borrow.setReturnDate(LocalDate.now());
        borrow.setStatus("RETURNED");
        borrowingRepository.save(borrow);

        return mapToResponse(borrow);
    }

    private BorrowingResponse mapToResponse(BorrowingEntity borrow) {
        return BorrowingResponse.builder()
                .id(borrow.getId())
                .bookId(borrow.getBook().getId())
                .memberId(borrow.getMember().getId())
                .bookName(borrow.getBook().getBookName())
                .isbn(borrow.getBook().getIsbn())
                .memberName(borrow.getMember().getMemberName())
                .borrowDate(borrow.getBorrowDate())
                .returnDate(borrow.getReturnDate())
                .status(borrow.getStatus())
                .build();
    }
}
