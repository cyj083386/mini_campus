package com.dokuny.mini_campus.course.exception;

public class TakeCourseNotExistException extends RuntimeException{
    public TakeCourseNotExistException(String message) {
        super(message);
    }
}
