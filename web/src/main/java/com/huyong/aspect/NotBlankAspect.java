package com.huyong.aspect;

import com.huyong.annotation.NotBlank;
import com.huyong.exception.ParamBlankException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 描述: NotBlank切面
 *
 * @author huyong
 * @date 2020-02-20 11:55 上午
 */
public class NotBlankAspect extends AbstractAspectManager {
    public NotBlankAspect(AspectApi aspectApi) {
        super(aspectApi);
    }

    @Override
    protected Object execute(ProceedingJoinPoint pjp, Method method) throws Throwable {
        final Object[] args = pjp.getArgs();
        final Parameter[] parameters = method.getParameters();
        for (int i = 0; i < args.length; i++) {
            final Parameter parameter = parameters[i];
            final NotBlank annotation = parameter.getAnnotation(NotBlank.class);
            final String name = parameter.getName();
            final Object arg = args[i];
            boolean flag = null != annotation && (null == arg || (arg instanceof String && StringUtils.isBlank((String) arg)));
            if (flag) {
                throw new ParamBlankException(name + "参数异常！");
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
