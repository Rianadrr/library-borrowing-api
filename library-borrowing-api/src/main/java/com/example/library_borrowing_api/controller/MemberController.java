package com.example.library_borrowing_api.controller;

import com.example.library_borrowing_api.dto.borrowing.BorrowingResponse;
import com.example.library_borrowing_api.dto.member.CreateMemberRequest;
import com.example.library_borrowing_api.dto.member.MemberResponse;
import com.example.library_borrowing_api.entity.MemberEntity;
import com.example.library_borrowing_api.repository.MemberRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @PostMapping
    public ResponseEntity<MemberResponse> createMember(@Valid @RequestBody CreateMemberRequest request) {
        MemberEntity member = MemberEntity.builder()
                .memberName(request.getMemberName())
                .build();

        memberRepository.save(member);

        MemberResponse response = MemberResponse.builder()
                .id(member.getId())
                .memberName(member.getMemberName())
                .build();

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable Long id) {
        MemberEntity member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anggota tidak ditemukan"));

        MemberResponse response = MemberResponse.builder()
                .id(member.getId())
                .memberName(member.getMemberName())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        List<MemberEntity> members = memberRepository.findAll();

        List<MemberResponse> responses = members.stream()
                .map(member -> MemberResponse.builder()
                        .id(member.getId())
                        .memberName(member.getMemberName())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}/borrowings")
    public ResponseEntity<List<BorrowingResponse>> getBorrowingsByMember(@PathVariable Long id) {

        MemberEntity member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        List<BorrowingResponse> borrowings = member.getBorrowing().stream()
                .map(borrow -> BorrowingResponse.builder()
                        .id(borrow.getId())
                        .bookId(borrow.getBook().getId())
                        .memberId(member.getId())
                        .bookName(borrow.getBook().getBookName())
                        .isbn(borrow.getBook().getIsbn())
                        .memberName(member.getMemberName())
                        .borrowDate(borrow.getBorrowDate())
                        .returnDate(borrow.getReturnDate())
                        .status(borrow.getStatus())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(borrowings);
    }
}
