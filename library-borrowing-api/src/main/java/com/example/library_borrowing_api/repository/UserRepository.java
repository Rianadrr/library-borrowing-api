package com.example.library_borrowing_api.repository;

import com.example.library_borrowing_api.entity.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUserEntity, Long> {
  Optional<AppUserEntity> findByUsername(String username);
}
