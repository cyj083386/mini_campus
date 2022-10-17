package com.dokuny.mini_campus.member.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindPasswordRequest {

    @NotBlank
    private String id;

    @NotBlank
    private String name;
}
