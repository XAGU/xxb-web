package com.xagu.xxb.xxt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xagu.xxb.xxt.domain.XxtCourse;
import com.xagu.xxb.xxt.domain.XxtWork;

import java.util.List;

/**
 * @author xagu
 * Created on 2020/4/25
 * Email:xagu_qc@foxmail.com
 * Describe: 学习通课程
 */
public interface XxtWorkService {

    /**
     * 获取所有课程
     * @return
     */
    List<XxtWork> getAllWork(String courseId, String classId) throws JsonProcessingException;

    String redoWork(String url) throws JsonProcessingException;

    String addTime(String url,String time);
}
