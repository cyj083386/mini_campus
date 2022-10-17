package com.dokuny.mini_campus.member.dto;

import com.dokuny.mini_campus.member.entity.LoginHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginHistoryDto {

    private String userAgent;

    private String ip;

    private LocalDateTime loginAt;





}
