package com.huyong.aspect;

import com.huyong.constant.AuthCheckConstant;
import com.huyong.dao.entity.UserDO;
import com.huyong.dao.mapper.UserMapper;
import com.huyong.dao.module.UserBO;
import com.huyong.exception.AuthException;
import com.huyong.filter.JwtFilter;
import com.huyong.service.UserService;
import com.huyong.utils.AuthUtils;
import com.huyong.utils.JwtHelper;
import com.huyong.utils.ServletUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * 基本被装饰类，检测token是否合法
 * @author huyong
 */
@Component
public class AspectApiImpl implements AspectApi {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;

    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {
        HttpServletRequest request = ServletUtils.getRequest();
        boolean ignore = request.getMethod().equalsIgnoreCase(AuthCheckConstant.OPTIONS);
        if (ignore) {
            return null;
        }
        //等到请求头信息authorization信息
        String authHeader = request.getHeader(AuthCheckConstant.TOKEN);
        if (StringUtils.isNotEmpty(authHeader)) {
            if (!authHeader.startsWith(AuthCheckConstant.START)) {
                throw new AuthException("登录异常，请重新登录！");
            }
            String token = authHeader.substring(AuthCheckConstant.START.length());
            final Claims claims = JwtHelper.parseJWT(token);
            if (claims == null) {
                throw new AuthException("身份过期，请重新登录！");
            }
            UserBO userBO = claims2User(claims);
            List<UserDO> users = userMapper.queryByCondition(userService.convertBo2Do(userBO));
            if (CollectionUtils.isNotEmpty(users)) {
                UserDO user = users.get(0);
                user.setPassword(null);
                AuthUtils.setUser(userService.convertDo2Bo(user));
            } else {
                throw new AuthException("身份过期，请重新登录！");
            }
        }
        return null;
    }
    private UserBO claims2User(Claims claims) {
        return JwtFilter.getUserBO(claims);
    }
}
