package com.example.library_borrowing_api.dto.borrowing;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBorrowingRequest {

    @NotNull(message = "ID buku tidak boleh kosong")
    private Long bookId;

    @NotNull(message = "ID anggota tidak boleh kosong")
    private Long memberId;

    @NotNull(message = "Tanggal peminjaman tidak boleh kosong")
    private LocalDate borrowDate;

    @NotNull(message = "Status peminjaman tidak boleh kosong")
    private String status;
}
