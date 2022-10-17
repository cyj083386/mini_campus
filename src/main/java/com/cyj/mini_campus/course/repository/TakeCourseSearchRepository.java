package com.dokuny.mini_campus.course.repository;

import com.dokuny.mini_campus.course.dto.CourseListDto;
import com.dokuny.mini_campus.course.dto.TakeCourseDto;
import com.dokuny.mini_campus.course.entity.TakeCourse;
import com.dokuny.mini_campus.course.repository.cond.CourseSearchCondition;
import com.dokuny.mini_campus.course.repository.cond.TakeCourseSearchCondition;
import com.dokuny.mini_campus.course.type.TakeCourseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TakeCourseSearchRepository {

    Page<TakeCourseDto> search(TakeCourseSearchCondition cond, Pageable pageable);

}
