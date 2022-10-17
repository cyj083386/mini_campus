package com.dokuny.mini_campus.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TakeCourseInput {

    private Long courseId;
    private String userId;
}
