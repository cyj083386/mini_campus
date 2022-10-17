package com.dokuny.mini_campus.course.repository;

import com.dokuny.mini_campus.course.dto.CourseDto;
import com.dokuny.mini_campus.course.dto.CourseListDto;
import com.dokuny.mini_campus.course.repository.cond.CourseSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseSearchRepository {

    Page<CourseListDto> search(CourseSearchCondition cond, Pageable pageable);
    Page<CourseDto> searchFront(CourseSearchCondition cond, Pageable pageable);

}
