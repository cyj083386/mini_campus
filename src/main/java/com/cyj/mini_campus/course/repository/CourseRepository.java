package com.dokuny.mini_campus.course.repository;

import com.dokuny.mini_campus.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long>, CourseSearchRepository {

}
