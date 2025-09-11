package com.example.library_borrowing_api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
  private Long timestamp;
  private int status;
  private String error;      // "Unauthorized", "Forbidden", etc.
  private String message;    // detail human-readable
  private String path;       // request URI
  private String code;       // app-specific code (e.g., "JWT_EXPIRED")
}
