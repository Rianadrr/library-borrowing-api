package com.example.library_borrowing_api.dto.book;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CreateBookRequest {

    @Pattern(regexp = "^[0-9]{10,13}$", message = "ISBN harus terdiri dari 10 hingga 13 digit angka")
    private String isbn;

    @NotNull(message = "Book Name is mandatory")
    private String bookName;

}
