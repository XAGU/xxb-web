package com.xagu.xxb.xxt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xagu.xxb.common.web.base.BaseController;
import com.xagu.xxb.common.web.domain.ResuBean;
import com.xagu.xxb.common.web.domain.ResuTable;
import com.xagu.xxb.log.aspect.SysLogger;
import com.xagu.xxb.xxt.domain.XxtChapterWork;
import com.xagu.xxb.xxt.service.XxtChapterService;
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
 * Created on 2020/5/10
 * Email:xagu_qc@foxmail.com
 * Describe: 学习通章节相关操作
 */
@Validated
@RestController
@RequestMapping("xxt/course/chapter")
public class XxtChapterController extends BaseController {

    @Autowired
    XxtChapterService xxtChapterService;
    /**
     * Describe: 基础路径
     */
    private static String MODULE_PATH = "xxt/course/chapter";

    /**
     * 进入章节列表界面
     * @return
     */
    @GetMapping("main")
    @PreAuthorize("hasPermission('/xxt/course/chapter/select','xxt:course:chapter:select')")
    public ModelAndView main(ModelAndView modelAndView){
        modelAndView.setViewName(MODULE_PATH+"/main");
        return modelAndView;
    }

    /**
     * 获取所有的章节
     * @param classId
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping
    @PreAuthorize("hasPermission('/xxt/course/chapter/select','xxt:course:chapter:select')")
    public ResuTable getAllChapter(String classId) throws JsonProcessingException {
        return dataTable(xxtChapterService.getAllChapters(classId));
    }

    /**
     * 打回章节作业
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("redo")
    @SysLogger("章节测验打回")
    @PreAuthorize("hasPermission('/xxt/course/chapter/redo','xxt:course:chapter:redo')")
    public ResuBean redoWork(XxtChapterWork xxtChapterWork, String courseId, String classId, String accountId) throws JsonProcessingException {
        String msg = xxtChapterService.redoWork(xxtChapterWork, courseId, classId, accountId);
        return decide(true, msg, msg);
    }

    /**
     * 加时章节作业
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("addTime")
    @SysLogger("章节测验加时")
    @PreAuthorize("hasPermission('/xxt/course/chapter/addTime','xxt:course:chapter:addTime')")
    public ResuBean addTime(XxtChapterWork xxtChapterWork, String courseId, String classId, String accountId,String time) throws JsonProcessingException {
        String msg = xxtChapterService.addTime(xxtChapterWork, courseId, classId, accountId,time);
        return decide(true, msg, msg);
    }
}
