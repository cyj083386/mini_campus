package com.dokuny.mini_campus.course.exception;

public class MemberNotOwnTakeCourseException extends RuntimeException{
    public MemberNotOwnTakeCourseException(String message) {
        super(message);
    }
}
