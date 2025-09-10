package com.example.library_borrowing_api.dto.member;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMemberRequest {

    @NotBlank(message = "Nama anggota tidak boleh kosong")
    private String memberName;
}
