package com.dokuny.mini_campus.course.dto;

import com.dokuny.mini_campus.course.type.TakeCourseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TakeCourseDto {

    private Long id;
    private String subject;
    private String memberId;
    private String memberPhone;
    private TakeCourseStatus status;
    private LocalDateTime registeredAt;

}
