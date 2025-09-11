package com.example.library_borrowing_api.service.impl;

import com.example.library_borrowing_api.dto.borrowing.BorrowingResponse;
import com.example.library_borrowing_api.dto.member.CreateMemberRequest;
import com.example.library_borrowing_api.dto.member.MemberResponse;
import com.example.library_borrowing_api.entity.MemberEntity;
import com.example.library_borrowing_api.repository.MemberRepository;
import com.example.library_borrowing_api.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberResponse createMember(CreateMemberRequest request) {
        MemberEntity member = MemberEntity.builder()
                .memberName(request.getMemberName())
                .build();

        memberRepository.save(member);

        return MemberResponse.builder()
                .id(member.getId())
                .memberName(member.getMemberName())
                .build();
    }

    @Override
    public MemberResponse getMemberById(Long id) {
        MemberEntity member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anggota tidak ditemukan"));

        return MemberResponse.builder()
                .id(member.getId())
                .memberName(member.getMemberName())
                .build();
    }

    @Override
    public List<MemberResponse> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(member -> MemberResponse.builder()
                        .id(member.getId())
                        .memberName(member.getMemberName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<BorrowingResponse> getBorrowingsByMember(Long id) {
        MemberEntity member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        return member.getBorrowing().stream()
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
    }
}
