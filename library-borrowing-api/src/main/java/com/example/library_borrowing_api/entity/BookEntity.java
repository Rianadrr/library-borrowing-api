package com.example.library_borrowing_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "books")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^[0-9]{10,13}$", message = "ISBN harus terdiri dari 10 hingga 13 digit angka")
    private String isbn;

    @Column(nullable = false)
    private String bookName;

    @Column(nullable = false)
    private String bookStatus;

    @ManyToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BorrowingEntity> borrowing;






}
