package com.huyong.exception;

/**
 * 描述: 权限异常
 *
 * @author huyong
 * @date 2020-02-21 10:00 下午
 */
public class AuthException extends RuntimeException {
    public AuthException(){}
    public AuthException(String msg) {
        super(msg);
    }
}
