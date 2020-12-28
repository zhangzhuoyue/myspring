package com.zzy.annocation;

import java.lang.annotation.*;

/**
 * @author zhangyue666
 * @description
 * @date 2020/12/22 21:31
 */
@Target({ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
    String value() default "";
}
