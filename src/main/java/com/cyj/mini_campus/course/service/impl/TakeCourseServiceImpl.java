package com.dokuny.mini_campus.course.service.impl;

import com.dokuny.mini_campus.commons.dto.ResponseResult;
import com.dokuny.mini_campus.commons.dto.SearchInput;
import com.dokuny.mini_campus.course.dto.TakeCourseCancelInput;
import com.dokuny.mini_campus.course.dto.TakeCourseDto;
import com.dokuny.mini_campus.course.dto.TakeCourseInput;
import com.dokuny.mini_campus.course.entity.Course;
import com.dokuny.mini_campus.course.entity.TakeCourse;
import com.dokuny.mini_campus.course.exception.CourseNotExistException;
import com.dokuny.mini_campus.course.exception.MemberNotOwnTakeCourseException;
import com.dokuny.mini_campus.course.exception.TakeCourseAlreadyExistException;
import com.dokuny.mini_campus.course.exception.TakeCourseNotExistException;
import com.dokuny.mini_campus.course.repository.CourseRepository;
import com.dokuny.mini_campus.course.repository.TakeCourseRepository;
import com.dokuny.mini_campus.course.repository.cond.TakeCourseSearchCondition;
import com.dokuny.mini_campus.course.service.TakeCourseService;
import com.dokuny.mini_campus.course.type.TakeCourseStatus;
import com.dokuny.mini_campus.member.entity.Member;
import com.dokuny.mini_campus.member.exception.MemberException;
import com.dokuny.mini_campus.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@RequiredArgsConstructor
@Service
public class TakeCourseServiceImpl implements TakeCourseService {

    private final TakeCourseRepository takeCourseRepository;
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<TakeCourseDto> list(SearchInput input, Pageable pageable) {
        return takeCourseRepository.search(TakeCourseSearchCondition.of(input), pageable);
    }

    @Transactional
    @Override
    public boolean updateStatus(Long id, String status) {

        TakeCourse takeCourse = takeCourseRepository.findById(id)
                .orElseThrow(() -> new TakeCourseNotExistException("수강신청정보가 존재하지 않습니다."));

        takeCourse.setStatus(TakeCourseStatus.valueOf(status));

        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<TakeCourseDto> getMyCourses(String memberId, Pageable pageable) {
        return takeCourseRepository.search(TakeCourseSearchCondition.of(memberId),pageable);
    }

    @Transactional
    @Override
    public ResponseResult req(TakeCourseInput input) {

        try {
            Course course = courseRepository.findById(input.getCourseId())
                    .orElseThrow(() -> new CourseNotExistException("존재하지 않는 강의입니다."));

            Member member = memberRepository.findById(input.getUserId())
                    .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

            if (takeCourseRepository.existsByMember_IdAndCourse_IdAndStatusIn(
                    input.getUserId(),
                    input.getCourseId(),
                    Arrays.asList(TakeCourseStatus.REQUEST, TakeCourseStatus.COMPLETE))) {
                throw new TakeCourseAlreadyExistException("이미 수강중인 강의입니다.");
            }

            takeCourseRepository.save(TakeCourse.builder()
                    .course(course)
                    .member(member)
                    .payPrice(course.getPrice())
                    .status(TakeCourseStatus.REQUEST)
                    .build());
            return ResponseResult.builder().result(true).build();

        } catch (Exception e) {
            return ResponseResult.builder().result(false).message(e.getMessage()).build();
        }
    }

    @Transactional
    @Override
    public ResponseResult cancelTakeCourse(TakeCourseCancelInput input) {
        try {
            TakeCourse takeCourse = takeCourseRepository.findById(input.getTakeCourseId())
                    .orElseThrow(() -> new TakeCourseNotExistException("존재하지 않는 수강 정보입니다."));

            if (!takeCourse.getMember().getId().equals(input.getUserId())) {
                throw new MemberNotOwnTakeCourseException("회원에게 없는 강의 정보입니다.");
            }

            takeCourse.setStatus(TakeCourseStatus.CANCEL);

            return new ResponseResult(true, "수강 취소 되었습니다.");
        } catch (Exception e) {
            return new ResponseResult(false,e.getMessage());
        }
    }
}
