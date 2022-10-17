package com.dokuny.mini_campus.course.exception;

public class CourseNotExistException extends RuntimeException {
    public CourseNotExistException(String message) {
        super(message);
    }
}
