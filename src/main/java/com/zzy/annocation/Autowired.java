package com.zzy.annocation;

import java.lang.annotation.*;

/**
 * @author zhangyue666
 * @description
 * @date 2020/12/22 21:34
 */
@Documented
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
    boolean value() default true;
}
