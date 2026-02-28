package com.app.ggshop.v1.common.exception.handler;

import com.app.ggshop.v1.common.exception.LoginFailException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice(basePackages = "com.app.ggshop.v1.controller.member")
public class MemberExceptionHandler {
    @ExceptionHandler(LoginFailException.class)
    protected RedirectView loginFail(LoginFailException loginFailException, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("login", "fail");
        return new RedirectView("/member/login");
    }
}
