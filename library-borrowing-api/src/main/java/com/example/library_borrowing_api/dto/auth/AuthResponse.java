package com.example.library_borrowing_api.dto.auth;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class AuthResponse {
  private String token;
  private String tokenType;
  private long   expiresInMs;
}
