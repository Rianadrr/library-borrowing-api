package com.example.library_borrowing_api.dto.member;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {

    private Long id;
    private String memberName;
}
