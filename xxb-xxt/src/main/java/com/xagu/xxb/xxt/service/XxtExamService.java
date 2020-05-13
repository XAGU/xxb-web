package com.xagu.xxb.xxt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xagu.xxb.xxt.domain.XxtExam;

import java.util.List;

/**
 * @author xagu
 * Created on 2020/4/25
 * Email:xagu_qc@foxmail.com
 * Describe: 学习通课程
 */
public interface XxtExamService {

    /**
     * 获取所有Exam
     *
     * @return
     */
    List<XxtExam> getAllExam(String courseId, String classId, String accountId) throws JsonProcessingException;

    String redoExam(String url,String accountId) throws JsonProcessingException;
}
