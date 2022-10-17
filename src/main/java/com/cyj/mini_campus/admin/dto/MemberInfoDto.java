package com.dokuny.mini_campus.admin.dto;

import com.dokuny.mini_campus.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MemberInfoDto {

    private String id;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "번호는 필수 입력 값입니다.")
    private String phone;


    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    private String zipcode;
    private String addr;
    private String addrDetail;


    public static MemberInfoDto of(Member member) {
        return MemberInfoDto.builder().
                id(member.getId()).
                name(member.getName()).
                phone(member.getPhone()).
                zipcode(member.getZipcode())

                .addr(member.getAddr())
                .addrDetail(member.getAddrDetail()).
                build();
    }
}
