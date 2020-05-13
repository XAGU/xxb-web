package com.xagu.xxb.log.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xagu
 * Created on 2020/5/4
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SysLogger {
    String value() default "unknown";
}
