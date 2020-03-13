package com.huyong.controller;

import com.huyong.annotation.NotBlank;
import com.huyong.annotation.ValidationParam;
import com.huyong.exception.CommonException;
import com.huyong.utils.ServletUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 描述: test
 *
 * @author huyong
 * @date 2020-03-04 10:45 下午
 */
@Controller
public class Test {
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        final HttpServletRequest request = ServletUtils.getRequest();
        final String token1 = request.getHeader("token");
        final Enumeration<String> headerNames = request.getHeaderNames();
        return "hello world";
    }
    @GetMapping("/test1")
    @ResponseBody
    public String test1(@NotBlank String name) {
        final HttpServletRequest request = ServletUtils.getRequest();
        return "hello world";
    }
}
