package com.xagu.xxb.controller;

import com.xagu.xxb.common.tools.serial.SnowFlake;
import com.xagu.xxb.common.web.base.BaseController;
import com.xagu.xxb.common.web.domain.ResuBean;
import com.xagu.xxb.system.domain.SysUser;
import com.xagu.xxb.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author xagu
 * Created on 2020/4/19
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
@RestController
public class RegisterController extends BaseController {

    @Autowired
    SysUserService sysUserService;

    @GetMapping("register")
    public ModelAndView registerPage(ModelAndView modelAndView) {
        modelAndView.setViewName("register");
        return modelAndView;
    }

    /**
     * Describe: 用户注册接口
     * Param ModelAndView
     * Return 操作结果
     */
    @PostMapping("register")
    @Transactional
    public ResuBean save(@Validated(value = SysUser.Insert.class) SysUser sysUser) {
        //未登录
        sysUser.setLogin("0");
        //前端不要性别
        sysUser.setSex("0");
        //注册用户enable为0
        sysUser.setEnable("0");
        //默认普通用户
        sysUser.setRoleIds("3");
        sysUser.setUserId("" + new SnowFlake().nextId());
        sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getPassword()));
        boolean result = sysUserService.save(sysUser);
        return decide(
                result,
                "注册成功",
                "注册失败"
        );
    }
}
