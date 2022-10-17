package com.dokuny.mini_campus.course.controller;

import com.dokuny.mini_campus.commons.dto.ResponseResult;
import com.dokuny.mini_campus.course.dto.TakeCourseCancelInput;
import com.dokuny.mini_campus.course.dto.TakeCourseInput;
import com.dokuny.mini_campus.course.service.CourseService;
import com.dokuny.mini_campus.course.service.TakeCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
public class ApiCourseController {

    private final TakeCourseService takeCourseService;

    @PostMapping("/api/course/req.api")
    public ResponseEntity<?> courseReq(@RequestBody TakeCourseInput input, Principal principal) {

        input.setUserId(principal.getName());

        ResponseResult result = takeCourseService.req(input);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/api/member/course/cancel.api")
    public ResponseEntity<?> cancelTakeCourse(@RequestBody TakeCourseCancelInput input, Principal principal) {
        input.setUserId(principal.getName());

        ResponseResult result = takeCourseService.cancelTakeCourse(input);

        return ResponseEntity.ok().body(result);
    }
}
