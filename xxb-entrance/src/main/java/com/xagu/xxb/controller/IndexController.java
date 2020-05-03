package com.xagu.xxb.controller;

import org.springframework.stereotype.Controller;
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
    public String index(){
        return "index";
    }
}
