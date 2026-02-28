package com.app.ggshop.v1.common.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoginFailException extends RuntimeException {
    public LoginFailException(String messge){super(messge);}
}
