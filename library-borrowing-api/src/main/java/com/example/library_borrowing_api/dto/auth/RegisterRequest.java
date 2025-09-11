package com.example.library_borrowing_api.dto.auth;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RegisterRequest {
  @NotBlank @Size(min = 3, max = 50)
  private String username;

  @NotBlank @Size(min = 6, max = 100)
  private String password;
}
