package com.zzy.annocation;

import java.lang.annotation.*;

/**
 * @author zhangyue666
 * @description
 * @date 2020/12/22 21:35
 */
@Target({ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Transactional {
    String value() default "TransactionManager";
}
