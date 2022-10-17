package com.dokuny.mini_campus.course.service.impl;

import com.dokuny.mini_campus.admin.entity.Category;
import com.dokuny.mini_campus.admin.exception.CategoryNotExistException;
import com.dokuny.mini_campus.admin.repository.CategoryRepository;
import com.dokuny.mini_campus.commons.dto.SearchInput;

import com.dokuny.mini_campus.commons.repository.FileUploadRepository;
import com.dokuny.mini_campus.commons.service.FileUploadService;
import com.dokuny.mini_campus.course.dto.*;
import com.dokuny.mini_campus.course.entity.Course;
import com.dokuny.mini_campus.course.exception.CourseNotExistException;

import com.dokuny.mini_campus.course.repository.CourseRepository;
import com.dokuny.mini_campus.course.repository.TakeCourseRepository;
import com.dokuny.mini_campus.course.repository.cond.CourseSearchCondition;
import com.dokuny.mini_campus.course.service.CourseService;

import com.dokuny.mini_campus.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public boolean add(CourseInput input) {

        Category category = categoryRepository.findById(input.getCategoryId())
                .orElseThrow(() -> new CategoryNotExistException("존재하지 않는 카테고리입니다."));

        String imgPath = getImgPath(input.getContents());

        if (!imgPath.equals("")) {
            input.setImagePath(imgPath);
        }

        courseRepository.save(Course.builder()
                .subject(input.getSubject())
                .category(category)
                .contents(input.getContents())
                .imagePath(input.getImagePath())
                .price(input.getPrice())
                .salePrice(input.getSalePrice())
                .saleEndAt(LocalDateTime.parse(input.getSaleEndAt()))
                .keyword(input.getKeyword())
                .summary(input.getSummary())
                .build());

        return true;
    }

    private String getImgPath(String contents) {
        Pattern nonValidPattern = Pattern
                .compile("(?i)< *[IMG][^\\>]*[src] *= *[\"\']{0,1}([^\"\'\\ >]*)");
        int imgCnt = 0;
        String img = "";
        Matcher matcher = nonValidPattern.matcher(contents);
        while (matcher.find()) {
            img = matcher.group(1);
            imgCnt++;
            if(imgCnt == 1){
                break;
            }
        }
        return img;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CourseListDto> list(SearchInput input, Pageable pageable) {
        return courseRepository.search(CourseSearchCondition.of(input), pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public CourseDto find(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotExistException("존재하지 않는 강의입니다."));
        return CourseDto.of(course);
    }

    @Transactional
    @Override
    public boolean edit(CourseInput input) {
        Course course = courseRepository.findById(input.getId())
                .orElseThrow(() -> new CourseNotExistException("존재하지 않는 강의입니다."));

        Category category = categoryRepository.findById(input.getCategoryId())
                .orElseThrow(() -> new CategoryNotExistException("존재하지 않는 카테고리입니다."));

        String imgPath = getImgPath(input.getContents());

        if (!imgPath.equals("")) {
            input.setImagePath(imgPath);
        }

        course.setCategory(category);
        course.setImagePath(input.getImagePath());
        course.setKeyword(input.getKeyword());
        course.setSubject(input.getSubject());
        course.setSummary(input.getSummary());
        course.setContents(input.getContents());
        course.setPrice(input.getPrice());
        course.setSalePrice(input.getSalePrice());
        course.setSaleEndAt(LocalDateTime.parse(input.getSaleEndAt()));

        return true;
    }

    @Override
    public boolean delete(String idList) {
        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");

            for (String s : ids) {
                long id = 0L;

                try {
                    id = Long.parseLong(s);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (id > 0) {
                    courseRepository.deleteById(id);
                }
            }
        }

        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CourseDto> frontList(SearchInput input, Pageable pageable) {
        return courseRepository.searchFront(CourseSearchCondition.of(input), pageable);
    }


}
