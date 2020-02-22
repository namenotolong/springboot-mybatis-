package com.huyong.aspect;

import com.alibaba.fastjson.JSONObject;
import com.huyong.annotation.ValidationParam;
import com.huyong.exception.ParamBlankException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 描述: 参数校验切面
 *
 * @author huyong
 * @date 2020-02-19 7:52 下午
 */
public class ValidationParamAspect extends AbstractAspectManager {
    public ValidationParamAspect(AspectApi aspectApi) {
        super(aspectApi);
    }

    @Override
    protected Object execute(ProceedingJoinPoint pjp, Method method) throws Throwable {
        final Object[] args = pjp.getArgs();
        final Parameter[] parameters = method.getParameters();
        for (int i = 0; i < args.length; i++) {
            final Parameter parameter = parameters[i];
            final ValidationParam annotation = parameter.getAnnotation(ValidationParam.class);
            //如果被参数该注解修饰
            if (null != annotation) {
                final String value = annotation.value();
                final String[] split = value.split(",");
                //用逗号分隔
                if (split.length > 0) {
                    final String name = parameter.getName();
                    final Object arg = args[i];
                    String flag;
                    if (arg instanceof JSONObject) {
                        flag = valid((JSONObject) arg, split);
                    } else {
                        flag = valid(arg, split);
                    }
                    if (flag != null){
                        throw new ParamBlankException(name + "中" + flag + "参数异常！");
                    }
                }
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

    /**
     * 返回不合法参数名称
     * @param jsonObject
     * @param value
     * @return
     */
    public String valid(JSONObject jsonObject, String[] value) {
        for (String s : value) {
            final Object arg = jsonObject.get(s.trim());
            boolean flag = null == arg || (arg instanceof String && StringUtils.isBlank((String) arg));
            if (flag) {
                return s.trim();
            }
        }
        return null;
    }
    public String valid(Object obj, String[] value) {
        final Class<?> aClass = obj.getClass();
        try {
            for (String s : value) {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(s.trim(), aClass);
                final Method readMethod = propertyDescriptor.getReadMethod();
                if (readMethod != null) {
                    final Object arg = readMethod.invoke(obj);
                    boolean flag = null == arg || (arg instanceof String && StringUtils.isBlank((String) arg));
                    if (flag) {
                        return s.trim();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
