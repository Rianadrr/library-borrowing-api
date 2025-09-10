package com.example.library_borrowing_api.dto.borrowing;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBorrowingRequest {

    @NotNull(message = "Status peminjaman tidak boleh kosong")
    private LocalDate returnDate;

    @NotNull(message = "Status peminjaman tidak boleh kosong")
    private String status;
}
