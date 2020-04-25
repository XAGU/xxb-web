package com.xagu.xxb.security.hander;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xagu.xxb.common.tools.servlet.ServletUtil;
import com.xagu.xxb.common.web.domain.ResuBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Describe: 自定义 Security 用户暂无权限处理类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    public static final Logger log = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        if(ServletUtil.isAjax(httpServletRequest)){
            ResuBean resuBean = new ResuBean();
            resuBean.setSuccess(false);
            resuBean.setMsg("暂无权限");
            resuBean.setCode(403);
            httpServletResponse.setHeader("Content-type","application/json;charset=UTF-8");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(resuBean));
        }else{
            httpServletResponse.sendRedirect("/error/403");
        }
    }
}
