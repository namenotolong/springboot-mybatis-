package com.huyong.filter;

import com.huyong.constant.AuthCheckConstant;
import com.huyong.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 描述: 用户过滤器
 *
 * @author huyong
 * @date 2020-02-20 4:08 下午
 */
@Component
@WebFilter(filterName = "user filter", urlPatterns = "/*")
public class UserFilter implements Filter {


    @Resource
    private UserService userService;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        //忽略options请求
        if (!httpRequest.getMethod().equalsIgnoreCase(AuthCheckConstant.OPTIONS)) {
            final String token = httpRequest.getHeader("token");
            if (StringUtils.isNotEmpty(token)) {
                
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
