package com.xagu.xxb.xxt.handler;

import com.xagu.xxb.common.web.domain.ResuBean;
import com.xagu.xxb.xxt.exception.XxtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * @author xagu
 * Created on 2020/4/28
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
@RestControllerAdvice
public class XxtExceptionHandler {
    @ExceptionHandler(XxtException.class)
    public ResuBean handle(XxtException exception) {
        ResuBean resuBean = new ResuBean();
        resuBean.setCode(exception.getCode());
        resuBean.setMsg(exception.getMessage());
        resuBean.setSuccess(false);
        return resuBean;
    }

}
