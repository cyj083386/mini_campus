package com.dokuny.mini_campus.admin.exception;

public class LoginHistoryNotExistException extends RuntimeException{

    public LoginHistoryNotExistException(String message) {
        super(message);
    }
}
