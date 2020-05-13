package com.xagu.xxb.log.controller;

import com.github.pagehelper.PageInfo;
import com.xagu.xxb.common.web.base.BaseController;
import com.xagu.xxb.common.web.domain.ResuTable;
import com.xagu.xxb.common.web.domain.request.PageDomain;
import com.xagu.xxb.log.domain.SysLog;
import com.xagu.xxb.log.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author xagu
 * Created on 2020/5/5
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
@Validated
@RequestMapping("system/log")
@RestController
public class SysLogController extends BaseController {
    /**
     * Describe: 基础路径
     */
    private static String MODULE_PATH = "system/log/";

    /**
     * Describe:
     */
    @Resource
    private SysLogService sysLogService;


    /**
     * Describe: 获取日志列表视图
     * Param ModelAndView
     * Return 日志列表视图
     */
    @GetMapping("main")
    @PreAuthorize("hasPermission('/system/log/select','sys:log:select')")
    public ModelAndView main(ModelAndView modelAndView) {
        modelAndView.setViewName(MODULE_PATH + "main");
        return modelAndView;
    }

    /**
     * Describe: 获取日志列表数据
     * Param SysRole PageDomain
     * Return 账户列表数据
     */
    @GetMapping
    @PreAuthorize("hasPermission('/system/log/select','sys:log:select')")
    public ResuTable data(PageDomain pageDomain, SysLog sysLog, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date minOperatime, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date maxOperatime) {
        PageInfo<SysLog> pageInfo = sysLogService.pageSelectByLoginUser(sysLog, minOperatime, maxOperatime, pageDomain);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }

}
