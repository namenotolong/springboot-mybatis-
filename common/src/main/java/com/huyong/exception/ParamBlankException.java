package com.huyong.exception;

/**
 * 描述: 空白参数异常
 *
 * @author huyong
 * @date 2020-02-19 3:44 下午
 */
public class ParamBlankException extends RuntimeException {
    public ParamBlankException(){
        super();
    }
    public ParamBlankException(String msg) {
        super(msg);
    }

}
