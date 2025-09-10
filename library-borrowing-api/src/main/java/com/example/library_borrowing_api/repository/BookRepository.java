package com.example.library_borrowing_api.repository;

import com.example.library_borrowing_api.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BookRepository extends JpaRepository<BookEntity , Long> {

}
