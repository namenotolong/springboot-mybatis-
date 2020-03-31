package com.huyong.config;

import com.huyong.annotation.*;
import com.huyong.aspect.*;
import com.huyong.utils.AuthUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 切面:防止xss攻击 记录log  参数验证
 * @author huyong
 */
@Aspect
@Configuration
public class ControllerAspect {

    @Resource
    private AspectApi aspectApi;

    @Pointcut("execution(* com.huyong.controller..*(..))  ")
    public void aspect() {
    }

    @Around(value = "aspect()")
    public Object validationPoint(ProceedingJoinPoint pjp)throws Throwable{
        final Class<?> clazz = pjp.getTarget().getClass();
        final MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = clazz.getMethod(signature.getName(), signature.getParameterTypes());
        //是否需要记录日志
        if (method.isAnnotationPresent(Log.class)) {
            aspectApi = new LogAspect(aspectApi);
        }
        //是否需要限流
        if (method.isAnnotationPresent(AccessLimit.class)) {
            aspectApi = new AccessLimitAspect(aspectApi);
        }
        //检测是否需要检测权限
        if (!method.isAnnotationPresent(Pass.class) && method.isAnnotationPresent(CheckAuth.class)) {
            aspectApi = new CheckAuthAspect(aspectApi);
        }
        //是否需要拦截xss攻击
        if(method.isAnnotationPresent( ParamXssPass.class )){
            aspectApi = new ParamXssPassAspect(aspectApi);
        }
        //是否需要检测对象参数
        if (checkParamAnnotation(method, ValidationParam.class)) {
            aspectApi = new ValidationParamAspect(aspectApi);
        }
        //是否需要检测单个参数
        if (checkParamAnnotation(method, NotBlank.class)) {
            aspectApi = new NotBlankAspect(aspectApi);
        }
        aspectApi.doHandlerAspect(pjp, method);
        return pjp.proceed(pjp.getArgs());
    }

    public boolean checkParamAnnotation(Method method, Class clazz) {
        final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (Annotation[] parameterAnnotation : parameterAnnotations) {
            for (Annotation annotation : parameterAnnotation) {
                final String simpleName = clazz.getSimpleName();
                if (annotation.toString().contains(simpleName + "(")) {
                    return true;
                }
            }
        }
        return false;
    }
}
