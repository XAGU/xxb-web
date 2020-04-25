package com.xagu.xxb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Describe: 错误页面控制器
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 *
 * @author xagu
 **/
@RequestMapping("error")
@RestController
public class ErrorController {

    @GetMapping("403")
    public ModelAndView noPermission(ModelAndView modelAndView){
        modelAndView.setViewName("error/403");
        return modelAndView;
    }
}
