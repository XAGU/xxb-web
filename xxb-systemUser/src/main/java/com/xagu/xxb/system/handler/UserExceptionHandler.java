package com.xagu.xxb.system.handler;

import com.xagu.xxb.common.web.domain.ResuBean;
import com.xagu.xxb.system.exception.UserException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xagu
 * Created on 2020/4/28
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(UserException.class)
    public ResuBean handle(UserException exception) {
        ResuBean resuBean = new ResuBean();
        resuBean.setCode(exception.getCode());
        resuBean.setMsg(exception.getMessage());
        resuBean.setSuccess(false);
        return resuBean;
    }

}
