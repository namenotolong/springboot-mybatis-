package com.huyong.common;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author huyong
 * @description
 **/

@Data
public class Result<T> {

    private  T data;

    private String message;

    private Integer code;

    private boolean success;

    public T getData() {
        return data;
    }

    public static Result error(String msg) {
        final Result<Object> result = new Result<>();
        result.setSuccess(false);
        result.setMessage(msg);
        return result;
    }

    public static Result success(Object data) {
        final Result<Object> result = new Result<>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

}

    
