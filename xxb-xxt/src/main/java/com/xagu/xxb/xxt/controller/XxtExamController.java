package com.xagu.xxb.xxt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xagu.xxb.common.web.base.BaseController;
import com.xagu.xxb.common.web.domain.ResuBean;
import com.xagu.xxb.common.web.domain.ResuTable;
import com.xagu.xxb.xxt.service.XxtExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("xxt/course/exam")
@RestController
public class XxtExamController extends BaseController {

    /**
     * Describe: 基础路径
     */
    private static String MODULE_PATH = "xxt/course/exam";

    @Autowired
    XxtExamService xxtExamService;

    @Autowired
    ObjectMapper objectMapper;


    @GetMapping("main")
    @PreAuthorize("hasPermission('/xxt/course/select','xxt:course:select')")
    public ModelAndView work(ModelAndView modelAndView) {
        modelAndView.setViewName(MODULE_PATH + "/main");
        return modelAndView;
    }

    @GetMapping
    @PreAuthorize("hasPermission('/xxt/course/select','xxt:course:select')")
    public ResuTable getAllWork(String courseId, String classId) throws JsonProcessingException {
        return dataTable(xxtExamService.getAllExam(courseId, classId));
    }

    @PostMapping("redo")
    @PreAuthorize("hasPermission('/xxt/course/select','xxt:course:select')")
    public ResuBean redoWork(String url) throws JsonProcessingException {
        String result = xxtExamService.redoExam(url);
        JsonNode readTree = objectMapper.readTree(result);
        //打回成功
        String msg = readTree.get("msg").asText();
        return decide("考试打回成功！".equals(msg), msg, msg);
    }
}
