package com.huyong.annotation;

import java.lang.annotation.*;

/**
 * @author huyong
 */
@Target(ElementType.PARAMETER)          // 可用在方法的参数上
@Retention(RetentionPolicy.RUNTIME)     // 运行时有效
@Documented
public @interface ValidationParam {
    /**
     * 必填参数,用逗号隔开
     */
    String value() default "";
}
