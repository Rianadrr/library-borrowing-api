package com.example.library_borrowing_api.service.impl;

import com.example.library_borrowing_api.dto.auth.AuthRequest;
import com.example.library_borrowing_api.dto.auth.AuthResponse;
import com.example.library_borrowing_api.dto.auth.RegisterRequest;
import com.example.library_borrowing_api.entity.AppUserEntity;
import com.example.library_borrowing_api.repository.UserRepository;
import com.example.library_borrowing_api.service.AuthService;
import com.example.library_borrowing_api.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthResponse register(RegisterRequest req) {
        String username = req.getUsername().trim().toLowerCase();
        userRepository.findByUsername(username).ifPresent(u -> {
            throw new IllegalArgumentException("Username already exists");
        });

        AppUserEntity user = AppUserEntity.builder()
                .username(username)
                .passwordHash(passwordEncoder.encode(req.getPassword()))
                .roles(new HashSet<>(Collections.singletonList("ROLE_USER")))
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user.getUsername(), Map.of("roles", user.getRoles()));
        return AuthResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .expiresInMs(jwtService.getExpirationMs())
                .build();
    }

    @Override
    public AuthResponse login(AuthRequest req) {
        String username = req.getUsername().trim().toLowerCase();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, req.getPassword()));

        String token = jwtService.generateToken(username, Map.of("roles", List.of("ROLE_USER", "ROL_BARU"), "contoh", "string aja"));
        return AuthResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .expiresInMs(jwtService.getExpirationMs())
                .build();
    }

    @Override
    public String getTokenInfo(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Missing or invalid Authorization header");
        }
        String token = authHeader.substring(7);
        return jwtService.getUsername(token);
    }
}
