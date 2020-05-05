package com.xagu.xxb.common.log;

import com.xagu.xxb.common.tools.security.SecurityUtil;
import com.xagu.xxb.common.tools.serial.SnowFlake;
import com.xagu.xxb.common.web.domain.SysLog;
import com.xagu.xxb.common.web.domain.SysUser;
import com.xagu.xxb.common.service.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author xagu
 * Created on 2020/5/4
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
@Aspect
@Component
public class SysLogAspect {
    @Pointcut("@annotation(com.xagu.xxb.common.log.SysLogger)")
    public void sysLogPoint() {
    }

    @Around("sysLogPoint()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        try {
            result = joinPoint.proceed();
            log(joinPoint, true);
        } catch (Throwable throwable) {
            log(joinPoint, false);
            throw throwable;
        }
        return result;
    }

    @Autowired
    SysLogService sysLogService;

    @Async
    public void log(ProceedingJoinPoint joinPoint, boolean isSuccess) {
        SysLog sysLog = new SysLog();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLogger sysLogger = method.getAnnotation(SysLogger.class);
        //操作名
        sysLog.setOperaevent(sysLogger.value());
        SysUser user = (SysUser) SecurityUtil.getLoginUser();
        //操作者
        sysLog.setUsername(user != null ? user.getUsername() : "unknown");
        //操作时间
        sysLog.setOperatime(new Date());
        //函数是否执行成功
        sysLog.setIssuccess(isSuccess);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 客户端ip
        String ip = request.getRemoteAddr();
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        sysLog.setClientip(ip);
        //操作ua
        sysLog.setUa(request.getHeader(HttpHeaders.USER_AGENT));
        //id
        sysLog.setLogId("" + new SnowFlake().nextId());
        sysLogService.insert(sysLog);
    }
}
