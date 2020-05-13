package com.xagu.xxb.xxt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xagu.xxb.log.aspect.SysLogger;
import com.xagu.xxb.common.web.base.BaseController;
import com.xagu.xxb.common.web.domain.ResuBean;
import com.xagu.xxb.common.web.domain.ResuTable;
import com.xagu.xxb.xxt.service.XxtWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotEmpty;

/**
 * @author xagu
 * Created on 2020/4/30
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
@Validated
@RequestMapping("xxt/course/work")
@RestController
public class XxtWorkController extends BaseController {

    /**
     * Describe: 基础路径
     */
    private static String MODULE_PATH = "xxt/course/work";

    @Autowired
    XxtWorkService xxtWorkService;

    @Autowired
    ObjectMapper objectMapper;


    @GetMapping("main")
    @PreAuthorize("hasPermission('/xxt/course/work/select','xxt:course:work:select')")
    public ModelAndView work(ModelAndView modelAndView) {
        modelAndView.setViewName(MODULE_PATH + "/main");
        return modelAndView;
    }

    @GetMapping
    @PreAuthorize("hasPermission('/xxt/course/work/select','xxt:course:work:select')")
    public ResuTable getAllWork(@NotEmpty(message = "courseId不能为空") String courseId, @NotEmpty(message = "classId不能为空") String classId,@NotEmpty(message = "accountId不能为空") String accountId) throws JsonProcessingException {
        return dataTable(xxtWorkService.getAllWork(courseId, classId,accountId));
    }

    @PostMapping("redo")
    @SysLogger("作业打回")
    @PreAuthorize("hasPermission('/xxt/course/work/redo','xxt:course:work:redo')")
    public ResuBean redoWork(@NotEmpty(message = "参数不能为空") String url,@NotEmpty(message = "accountId不能为空") String accountId) throws JsonProcessingException {
        String msg = xxtWorkService.redoWork(url,accountId);
        return decide(true, msg, msg);
    }

    @PostMapping("addTime")
    @SysLogger("作业加时")
    @PreAuthorize("hasPermission('/xxt/course/work/addTime','xxt:course:work:addTime')")
    public ResuBean addTime(String courseId, String clazzId, String taskrefId, String cpi, String time,@NotEmpty(message = "accountId不能为空") String accountId) throws JsonProcessingException {
        String msg = xxtWorkService.addTime(courseId, clazzId, taskrefId, cpi, time,accountId);
        return decide(true, msg, msg);
    }
}
