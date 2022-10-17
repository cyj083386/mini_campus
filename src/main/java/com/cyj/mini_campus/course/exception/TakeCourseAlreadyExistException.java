package com.dokuny.mini_campus.course.exception;

public class TakeCourseAlreadyExistException extends RuntimeException{
    public TakeCourseAlreadyExistException(String message) {
        super(message);
    }
}
