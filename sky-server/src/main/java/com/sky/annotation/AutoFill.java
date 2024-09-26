package com.sky.annotation;

/**
 *自定义注解，用于标识某个方法需要进行功能字段自动填充使用
 */


import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 标识这个只能加在方法前面
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    OperationType value();
}
