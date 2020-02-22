package com.huyong.filter;

import com.huyong.constant.AuthCheckConstant;
import com.huyong.service.UserService;
import com.huyong.utils.AuthUtils;
import com.huyong.utils.ServletUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        final Cookie[] cookies = httpRequest.getCookies();
        //如果含有该cookie，就必须正确，没有local中就没有user
        for (Cookie cookie : cookies) {
            if (AuthCheckConstant.AUTH_TICKET.equals(cookie.getName())) {
                final String value = cookie.getValue();
                final HttpServletResponse httpResponse = (HttpServletResponse) response;
                try {
                    //成功加入local，失败重新登录
                    final boolean valid = userService.valid(value);
                    if (!valid) {
                        AuthUtils.clear();
                        ServletUtils.removeCookie(cookie);
                        //重定向到认证页面
                        httpResponse.sendRedirect("");
                    }
                } catch (Exception e) {
                    AuthUtils.clear();
                    ServletUtils.removeCookie(cookie);
                    //解密失败重新登录
                    httpResponse.sendRedirect("");
                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
