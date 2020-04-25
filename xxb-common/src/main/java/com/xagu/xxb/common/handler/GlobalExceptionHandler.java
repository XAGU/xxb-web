package com.xagu.xxb.common.handler;

import com.xagu.xxb.common.web.domain.ResuBean;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.jws.WebResult;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Set;

/**
 * @author xagu
 * Created on 2020/4/23
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResuBean handle(ValidationException exception) {
        StringBuilder errorMsg = new StringBuilder();
        if (exception instanceof ConstraintViolationException) {
            ConstraintViolationException exs = (ConstraintViolationException) exception;

            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                //打印验证不通过的信息
                errorMsg.append(item.getMessage()).append(",");
            }
        }
        return getResuBean(errorMsg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResuBean resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        StringBuilder errorMsg = new StringBuilder();
        for (ObjectError objectError : objectErrors) {
            errorMsg.append(objectError.getDefaultMessage()).append(",");
        }
        return getResuBean(errorMsg);
    }

    private ResuBean getResuBean(StringBuilder errorMsg) {
        if (errorMsg.length() > 1) {
            errorMsg.deleteCharAt(errorMsg.length()-1);
        }
        ResuBean resuBean = new ResuBean();
        resuBean.setCode(HttpStatus.BAD_REQUEST.value());
        resuBean.setMsg(errorMsg.toString());
        resuBean.setSuccess(false);
        return resuBean;
    }
}
