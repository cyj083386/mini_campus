package com.dokuny.mini_campus.course.repository;

import com.dokuny.mini_campus.course.entity.TakeCourse;
import com.dokuny.mini_campus.course.type.TakeCourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TakeCourseRepository extends JpaRepository<TakeCourse,Long>,TakeCourseSearchRepository {

    boolean existsByMember_IdAndCourse_IdAndStatusIn(String memberId, Long courseId, Collection<TakeCourseStatus> statuses);
}
