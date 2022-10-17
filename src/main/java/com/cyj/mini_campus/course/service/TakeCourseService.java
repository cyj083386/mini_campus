package com.dokuny.mini_campus.course.service;

import com.dokuny.mini_campus.commons.dto.ResponseResult;
import com.dokuny.mini_campus.commons.dto.SearchInput;
import com.dokuny.mini_campus.course.dto.TakeCourseCancelInput;
import com.dokuny.mini_campus.course.dto.TakeCourseDto;
import com.dokuny.mini_campus.course.dto.TakeCourseInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TakeCourseService {

    Page<TakeCourseDto> list(SearchInput input, Pageable pageable);

    boolean updateStatus(Long id, String status);

    Page<TakeCourseDto> getMyCourses(String memberId,Pageable pageable);
    public ResponseResult req(TakeCourseInput input);
    public ResponseResult cancelTakeCourse(TakeCourseCancelInput input);
}
