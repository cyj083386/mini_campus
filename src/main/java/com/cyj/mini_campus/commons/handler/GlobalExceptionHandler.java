package com.dokuny.mini_campus.commons.handler;

import com.dokuny.mini_campus.member.dto.MemberRegisterRequest;
import com.dokuny.mini_campus.member.exception.MemberRegisterException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MemberRegisterException.class)
    public ModelAndView memberRegisterExceptionHandler(MemberRegisterException e) {

        ModelAndView mv = new ModelAndView();

        mv.setViewName("member/register");
        mv.setStatus(HttpStatus.BAD_REQUEST);
        mv.addObject("errorMessage", e.getMessage());
        mv.addObject("request", MemberRegisterRequest.builder().build());

        return mv;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView AllExceptionHandler(Exception e) {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("commons/error");
        mv.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        mv.addObject("errorMessage",e.getMessage());

        return mv;
    }

}
