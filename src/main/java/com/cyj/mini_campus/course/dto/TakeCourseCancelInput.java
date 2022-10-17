package com.dokuny.mini_campus.course.dto;


import lombok.Data;

@Data
public class TakeCourseCancelInput {

    private Long takeCourseId;
    private String userId;
}
