package com.huyong.aspect;

import com.huyong.annotation.CheckAuth;
import com.huyong.exception.AuthException;
import com.huyong.utils.AuthUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import java.io.IOException;
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
            final int value = annotation.value();
            switch (value) {
                case 1 : break;
                case 2 :
                case 3 :
                case 4 :
                    checkAuth(value);break;
                default:
                    log.warn("权限参数" + pjp.getTarget().getClass() + method.getName() + "异常！");
                    throw new AuthException("权限参数" + pjp.getTarget().getClass() + method.getName() + "异常！");
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
     * cud操作只能是3（操作自己的）、4，评论那些就是2
     */
    public void checkAuth(int value) throws IOException {
        if (null == AuthUtils.getUser()) {
            //重定向到登录
            //response.sendRedirect("http://www.baidu.com");
            return;
        }

    }
}
