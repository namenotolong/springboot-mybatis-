package com.huyong.filter;

import com.huyong.constant.AuthCheckConstant;
import com.huyong.dao.entity.UserDO;
import com.huyong.exception.AuthException;
import com.huyong.utils.AuthUtils;
import com.huyong.utils.JwtHelper;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * 描述: jwt过滤器
 *
 * @author huyong
 * @date 2020-03-12 3:11 下午
 */
@Component
public class JwtFilter extends GenericFilterBean {

    @Value("${audience.base64Secret}")
    private String secret;

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        //等到请求头信息authorization信息
        final String authHeader = request.getHeader("authorization");
        if (StringUtils.isNotEmpty(authHeader)) {
            if (!authHeader.startsWith(AuthCheckConstant.START)) {
                throw new AuthException("登录异常，请重新登录！");
            }
            String token = authHeader.substring(AuthCheckConstant.START.length());
            final Claims claims = JwtHelper.parseJWT(token, secret);
            if (claims == null) {
                throw new AuthException("身份过期，请重新登录！");
            }
            AuthUtils.setUser(claims2User(claims));
        }
        chain.doFilter(req, res);
    }

    private UserDO claims2User(Claims claims) {
        UserDO userDO = new UserDO();
        final Set<String> strings = claims.keySet();
        strings.remove("exp");
        strings.remove("nbf");
        for (String field : strings) {
            try {
                final PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field, userDO.getClass());
                final Method writeMethod = propertyDescriptor.getWriteMethod();
                if (writeMethod != null) {
                    writeMethod.invoke(userDO, claims.get(field));
                }
            } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return userDO;
    }
}
