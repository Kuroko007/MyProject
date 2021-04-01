package com.cmsk.wxplatform.common.auth;

import java.lang.annotation.*;

/**
 * @author zhanglgstart
 * @create 2021-03-08 11:13
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authorized {
    String value() default "";
}
