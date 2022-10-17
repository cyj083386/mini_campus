package com.dokuny.mini_campus.member.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberException extends RuntimeException{

    public MemberException(String message) {
        super(message);
        log.error(message);
    }
}
