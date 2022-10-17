package com.dokuny.mini_campus.course.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourseListDto {

    private Long id;
    private String subject;
    private String categoryName;
    private LocalDateTime registeredAt;

}
