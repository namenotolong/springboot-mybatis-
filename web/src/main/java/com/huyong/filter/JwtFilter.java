package com.huyong.filter;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 描述: jwt过滤器
 *
 * @author huyong
 * @date 2020-03-12 3:11 下午
 */
public class JwtFilter extends GenericFilterBean {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }
}
