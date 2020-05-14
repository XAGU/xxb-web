package com.xagu.xxb.xxt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xagu.xxb.common.tools.AesUtil;
import com.xagu.xxb.common.web.base.BaseController;
import com.xagu.xxb.common.web.domain.ResuBean;
import com.xagu.xxb.common.web.domain.ResuTable;
import com.xagu.xxb.log.aspect.SysLogger;
import com.xagu.xxb.xxt.exception.XxtException;
import com.xagu.xxb.xxt.service.XxtAnalysisService;
import com.xagu.xxb.xxt.service.XxtExamLibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @author xagu
 * Created on 2020/5/13
 * Email:xagu_qc@foxmail.com
 * Describe: 学情分析
 */
@Validated
@RequestMapping("xxt/course/analysis")
@RestController
public class XxtAnalysisController extends BaseController {

    @Autowired
    private XxtAnalysisService xxtAnalysisService;


    /**
     * Describe: 基础路径
     */
    private static String MODULE_PATH = "xxt/course/analysis";

    /**
     * 跳转课程列表界面
     *
     * @param modelAndView
     * @return
     */
    @GetMapping("main")
    @PreAuthorize("hasPermission('/xxt/course/select','xxt:course:select')")
    public ModelAndView main(ModelAndView modelAndView) {
        modelAndView.setViewName(MODULE_PATH + "/main");
        return modelAndView;
    }

    /**
     * 拿到enc
     *
     * @return
     */
    @GetMapping()
    @PreAuthorize("hasPermission('/xxt/course/analysis','xxt:course:analysis')")
    public ResuBean getExportEnc(String courseId, String classId, String accountId) throws JsonProcessingException {
        String enc = AesUtil.encrypt(xxtAnalysisService.getExportEnc(courseId, classId, accountId), AesUtil.KEY_DOWNLOAD_EXAM);
        return decide(enc != null, enc, "失败");
    }

    /**
     * 跳转成绩详情界面
     *
     * @param modelAndView
     * @return
     */
    @GetMapping("scoreStatistics")
    @PreAuthorize("hasPermission('/xxt/course/analysis/scoreStatistics','xxt:course:analysis:scoreStatistics')")
    public ModelAndView examLib(ModelAndView modelAndView) {
        modelAndView.setViewName(MODULE_PATH + "/scoreStatistics");
        return modelAndView;
    }


    /**
     * 获取成绩详情
     *
     * @return
     */
    @PostMapping("scoreStatistics")
    @PreAuthorize("hasPermission('/xxt/course/analysis/scoreStatistics','xxt:course:analysis:scoreStatistics')")
    public ResuTable getScoreStatistics(String courseId, String classId, String seltables, String code, String accountId) throws IOException {
        String enc = null;
        try {
            enc = AesUtil.decrypt(code, AesUtil.KEY_DOWNLOAD_EXAM);
        } catch (Exception e) {
            e.printStackTrace();
            //解密失败
            throw new XxtException(500, "获取成绩详情失败");
        }
        return dataTable(xxtAnalysisService.getscoreStatistics(courseId, classId, seltables, enc, accountId));
    }


}
