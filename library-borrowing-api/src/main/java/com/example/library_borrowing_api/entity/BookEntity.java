package com.example.library_borrowing_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(nullable = false)
    private int isbn;

    @Column(nullable = false)
    private String bookName;

    @Column(nullable = false)
    private String bookStatus;

    @ManyToMany
    private BorrowingEntity;






}
