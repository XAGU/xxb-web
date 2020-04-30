package com.xagu.xxb.xxt.exception;

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
public class XxtException extends RuntimeException {

    private Integer code;

    public XxtException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

}
