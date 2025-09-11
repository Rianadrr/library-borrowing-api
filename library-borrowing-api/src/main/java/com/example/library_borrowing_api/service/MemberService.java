package com.example.library_borrowing_api.service;

import com.example.library_borrowing_api.dto.borrowing.BorrowingResponse;
import com.example.library_borrowing_api.dto.member.CreateMemberRequest;
import com.example.library_borrowing_api.dto.member.MemberResponse;

import java.util.List;

public interface MemberService {
    MemberResponse createMember(CreateMemberRequest request);
    MemberResponse getMemberById(Long id);
    List<MemberResponse> getAllMembers();
    List<BorrowingResponse> getBorrowingsByMember(Long id);
}
