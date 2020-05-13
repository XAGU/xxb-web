package com.xagu.xxb.system.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xagu
 * Created on 2020/4/25
 * Email:xagu_qc@foxmail.com
 * Describe: 自定义学习通异常
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserException extends RuntimeException {

    private Integer code;

    public UserException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

}
