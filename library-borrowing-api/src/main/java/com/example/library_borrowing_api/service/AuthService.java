package com.example.library_borrowing_api.service;

import com.example.library_borrowing_api.dto.auth.AuthRequest;
import com.example.library_borrowing_api.dto.auth.AuthResponse;
import com.example.library_borrowing_api.dto.auth.RegisterRequest;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(AuthRequest request);

    String getTokenInfo(HttpServletRequest request);
}
