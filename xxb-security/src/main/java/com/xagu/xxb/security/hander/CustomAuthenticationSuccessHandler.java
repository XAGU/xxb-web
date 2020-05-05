package com.xagu.xxb.security.hander;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xagu.xxb.common.log.SysLogger;
import com.xagu.xxb.common.web.domain.ResuBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Describe: 自定义 Security 用户未登陆处理类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    public static final Logger log = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Autowired
    ObjectMapper objectMapper;

    @Override
    @SysLogger("登录成功")
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ResuBean resuBean = new ResuBean();
        resuBean.setSuccess(true);
        resuBean.setMsg("登陆成功");
        resuBean.setCode(200);
        httpServletResponse.setHeader("Content-type","application/json;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(resuBean));

    }
}
