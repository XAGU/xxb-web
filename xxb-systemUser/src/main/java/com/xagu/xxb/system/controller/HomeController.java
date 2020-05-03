package com.xagu.xxb.system.controller;

import com.xagu.xxb.common.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xagu
 * Created on 2020/4/20
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
@Controller
public class HomeController extends BaseController {

    @GetMapping("home")
    public String home(){
        return "home";
    }
}
