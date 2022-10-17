package com.dokuny.mini_campus.course.dto;

import com.dokuny.mini_campus.course.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CourseDto {

    private Long id;

    private String categoryName;

    private long categoryId;

    private String imagePath;

    private String keyword;

    private String subject;

    private String summary;

    private String contents;

    private long price;

    private long salePrice;

    private LocalDateTime saleEndAt;


    public static CourseDto of(Course course) {

        return CourseDto.builder()
                .id(course.getId())
                .imagePath(course.getImagePath())
                .keyword(course.getKeyword())
                .subject(course.getSubject())
                .summary(course.getSummary())
                .contents(course.getContents())
                .price(course.getPrice())
                .salePrice(course.getSalePrice())
                .saleEndAt(course.getSaleEndAt())
                .categoryName(course.getCategory().getName())
                .build();
    }


}
