package com.xagu.xxb.xxt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xagu.xxb.common.tools.AesUtil;
import com.xagu.xxb.common.web.base.BaseController;
import com.xagu.xxb.common.web.domain.ResuBean;
import com.xagu.xxb.common.web.domain.ResuTable;
import com.xagu.xxb.log.aspect.SysLogger;
import com.xagu.xxb.xxt.exception.XxtException;
import com.xagu.xxb.xxt.service.XxtExamLibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author xagu
 * Created on 2020/5/13
 * Email:xagu_qc@foxmail.com
 * Describe: 试卷库
 */
@Validated
@RequestMapping("xxt/course/examLib")
@RestController
public class XxtExamLibController extends BaseController {

    @Autowired
    private XxtExamLibService xxtExamLibService;


    /**
     * Describe: 基础路径
     */
    private static String MODULE_PATH = "xxt/course/examLib";

    /**
     * 跳转试卷答案列表界面
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
     * 跳转试卷答案列表界面
     *
     * @param modelAndView
     * @return
     */
    @GetMapping("examLib")
    @PreAuthorize("hasPermission('/xxt/course/examLib','xxt:course:examLib:select')")
    public ModelAndView examLib(ModelAndView modelAndView) {
        modelAndView.setViewName(MODULE_PATH + "/examLib");
        return modelAndView;
    }

    /**
     * 获取所有试卷库
     *
     * @return
     */
    @GetMapping()
    @PreAuthorize("hasPermission('/xxt/course/examLib','xxt:course:examLib:select')")
    public ResuTable getExams(String courseId, String classId, String accountId) throws JsonProcessingException {
        return dataTable(xxtExamLibService.getExamLib(courseId, classId, accountId));
    }

    /**
     * 获取所有试卷库
     *
     * @return
     */
    @GetMapping("download")
    @SysLogger("试卷下载")
    @PreAuthorize("hasPermission('/xxt/course/examLib/download','xxt:course:examLib:download')")
    public ResuBean downloadExam(String url, ModelAndView modelAndView) {
        String retUrl = null;
        try {
            //
            retUrl = AesUtil.decrypt(url, AesUtil.KEY_DOWNLOAD_EXAM);
        } catch (Exception e) {
            e.printStackTrace();
            //解密失败
            throw new XxtException(500, "获取下载链接失败！");
        }
        return success(retUrl);
    }

}
