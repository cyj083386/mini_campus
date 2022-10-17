package com.dokuny.mini_campus.member.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberRegisterException extends MemberException{

    public MemberRegisterException(String message) {
        super(message);
        log.error(message);
    }
}
