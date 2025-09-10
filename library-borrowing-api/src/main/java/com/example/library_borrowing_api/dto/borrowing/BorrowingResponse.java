package com.example.library_borrowing_api.dto.borrowing;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingResponse {
    private Long id;
    private Long bookId;
    private Long memberId;
    private String bookName;
    private String isbn;
    private String memberName;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String status;
}
