package com.huyong.filter;

import com.huyong.dao.module.UserBO;
import com.huyong.utils.AuthUtils;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {
        chain.doFilter(req, res);
        AuthUtils.clear();
    }

    private UserBO claims2User(Claims claims) {
        return getUserBO(claims);
    }

    public static UserBO getUserBO(Claims claims) {
        UserBO user = new UserBO();
        final Set<String> strings = claims.keySet();
        strings.remove("exp");
        strings.remove("nbf");
        for (String field : strings) {
            try {
                final PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field, user.getClass());
                final Method writeMethod = propertyDescriptor.getWriteMethod();
                if (writeMethod != null) {
                    if ("id".equals(field)) {
                        writeMethod.invoke(user, Long.valueOf(String.valueOf(claims.get(field))));
                    } else {
                        writeMethod.invoke(user, claims.get(field));
                    }
                }
            } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}
