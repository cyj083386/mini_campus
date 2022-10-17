package com.dokuny.mini_campus.course.service;

import com.dokuny.mini_campus.commons.dto.SearchInput;
import com.dokuny.mini_campus.course.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {

    boolean add(CourseInput input);

    Page<CourseListDto> list(SearchInput input, Pageable pageable);

    CourseDto find(Long id);

    boolean edit(CourseInput input);

    boolean delete(String idList);

    Page<CourseDto> frontList(SearchInput input,Pageable pageable);

}
