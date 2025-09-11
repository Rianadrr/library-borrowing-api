package com.example.library_borrowing_api.controller;

import com.example.library_borrowing_api.dto.auth.AuthRequest;
import com.example.library_borrowing_api.dto.auth.AuthResponse;
import com.example.library_borrowing_api.dto.auth.RegisterRequest;
import com.example.library_borrowing_api.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest req) {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @GetMapping("/me")
    public String tokenInfo(HttpServletRequest request) {
        return authService.getTokenInfo(request);
    }
}
