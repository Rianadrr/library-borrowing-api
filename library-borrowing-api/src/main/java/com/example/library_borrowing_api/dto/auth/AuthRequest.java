package com.example.library_borrowing_api.dto.auth;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AuthRequest {
  @NotBlank private String username;
  @NotBlank private String password;
}
