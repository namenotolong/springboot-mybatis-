package com.huyong.advice;

import com.huyong.common.Result;
import com.huyong.utils.AuthUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 描述: 返回体控制
 *
 * @author huyong
 * @date 2020-02-19 6:17 下午
 */
@ControllerAdvice
public class ResultBodyAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return returnType.hasMethodAnnotation(ResponseBody.class);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (o instanceof Result){
            return o;
        }
        //swagger接口加上豁免
        if(request.getURI().toString().contains("swagger") || request.getURI().toString().contains("api-docs")){
            return o;
        }
        return Result.success(o);
    }
}
