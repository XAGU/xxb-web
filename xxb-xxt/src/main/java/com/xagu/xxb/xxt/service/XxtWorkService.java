package com.xagu.xxb.xxt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
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
     *
     * @return
     */
    List<XxtWork> getAllWork(String courseId, String classId, String accountId) throws JsonProcessingException;

    String redoWork(String url, String accountId) throws JsonProcessingException;

    String addTime(String courseId, String clazzId, String taskrefId, String cpi, String time, String accountId) throws JsonProcessingException;
}
