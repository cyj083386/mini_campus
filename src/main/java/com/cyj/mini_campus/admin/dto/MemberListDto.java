package com.dokuny.mini_campus.admin.dto;


import com.dokuny.mini_campus.commons.role.Role;
import com.dokuny.mini_campus.member.entity.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberListDto {

    private String id;

    private String name;

    private String phone;

    private LocalDateTime registeredAt;

    private Role role;

    private LocalDateTime lastLoginAt;



    public static MemberListDto fromMember(Member member) {
        return MemberListDto.builder()
                .id(member.getId())
                .name(member.getName())
                .phone(member.getPhone())
                .registeredAt(member.getCreatedAt())
                .role(member.getRole())
                .build();
    }
}
