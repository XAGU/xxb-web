package com.xagu.xxb.xxt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xagu.xxb.common.web.base.BaseController;
import com.xagu.xxb.common.web.domain.ResuTable;
import com.xagu.xxb.xxt.service.XxtCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author xagu
 * Created on 2020/4/30
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
@Validated
@RequestMapping("xxt/course")
@RestController
public class XxtCourseController extends BaseController {

    /**
     * Describe: 基础路径
     */
    private static String MODULE_PATH = "xxt/course/";

    @Autowired
    XxtCourseService xxtCourseService;

    /**
     * Describe: 获取权限列表视图
     * Param ModelAndView
     * Return 权限列表视图
     */
    @GetMapping("main")
    @PreAuthorize("hasPermission('/xxt/course/select','xxt:course:select')")
    public ModelAndView main(ModelAndView modelAndView) {
        modelAndView.setViewName(MODULE_PATH + "main");
        return modelAndView;
    }

    @GetMapping
    @PreAuthorize("hasPermission('/xxt/course/select','xxt:course:select')")
    public ResuTable getAllCourse() throws JsonProcessingException {
        return dataTable(xxtCourseService.getAllCourse());
    }
}
