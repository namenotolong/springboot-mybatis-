package com.huyong.aspect;

import com.huyong.annotation.CheckAuth;
import com.huyong.enums.RoleEnum;
import com.huyong.exception.CommonException;
import com.huyong.utils.AuthUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import java.lang.reflect.Method;

/**
 * 描述: 检测用户权限切面
 *
 * @author huyong
 * @date 2020-02-21 9:34 下午
 */
@Slf4j
public class CheckAuthAspect extends AbstractAspectManager{
    public CheckAuthAspect(AspectApi aspectApi) {
        super(aspectApi);
    }

    @Override
    protected Object execute(ProceedingJoinPoint pjp, Method method) throws Throwable {
        CheckAuth annotation = method.getAnnotation(CheckAuth.class);
        if (annotation != null) {
            boolean valid = AuthUtils.getUser().getRole().equals(RoleEnum.ADMIN.getCode());
            if (!valid) {
                throw new CommonException("权限不足！");
            }
        }
        return null;
    }

    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {
        super.doHandlerAspect(pjp,method);
        execute(pjp,method);
        return null;
    }
}
