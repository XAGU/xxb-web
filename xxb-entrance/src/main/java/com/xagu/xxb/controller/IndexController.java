package com.xagu.xxb.controller;

import com.xagu.xxb.common.tools.security.SecurityUtil;
import com.xagu.xxb.system.domain.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xagu
 * Created on 2020/4/19
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
@Controller
public class IndexController {

    @GetMapping("index")
    public String index(Model model){
        SysUser user = (SysUser) SecurityUtil.getLoginUser();
        model.addAttribute("username",user.getUsername());
        return "index";
    }
}
