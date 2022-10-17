package com.dokuny.mini_campus.course.dto;

import lombok.Data;

@Data
public class CourseInput {


    private long id;
    private long categoryId;

    private String imagePath;

    private String keyword;

    private String subject;

    private String summary;

    private String contents;

    private long price;

    private long salePrice;

    private String saleEndAt;


}
