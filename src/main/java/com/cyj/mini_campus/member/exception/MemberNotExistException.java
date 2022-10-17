package com.dokuny.mini_campus.member.exception;

public class MemberNotExistException extends RuntimeException{
    public MemberNotExistException(String message) {
        super(message);
    }
}
