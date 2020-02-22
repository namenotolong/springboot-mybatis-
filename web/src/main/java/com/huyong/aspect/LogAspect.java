package com.huyong.aspect;

import com.huyong.dao.entity.UserDO;
import com.huyong.utils.AuthUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 描述: 记录日志切面
 *
 * @author huyong
 * @date 2020-02-22 2:39 下午
 */
public class LogAspect extends AbstractAspectManager {
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);
    public LogAspect(AspectApi aspectApi) {
        super(aspectApi);
    }

    @Override
    protected Object execute(ProceedingJoinPoint pjp, Method method) throws Throwable {
        final UserDO user = AuthUtils.getUser();
        log.info("用户{}，进入到了{}", user, pjp.getTarget().getClass() + method.getName());
        return null;
    }

    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {
        super.doHandlerAspect(pjp,method);
        execute(pjp,method);
        return null;
    }
}
