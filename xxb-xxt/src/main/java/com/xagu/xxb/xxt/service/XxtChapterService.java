package com.xagu.xxb.xxt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.xagu.xxb.xxt.domain.XxtChapterWork;

/**
 * @author xagu
 * Created on 2020/5/10
 * Email:xagu_qc@foxmail.com
 * Describe: 章节相关操作
 */
public interface XxtChapterService {

    /**
     * 获取所有的章节
     * @param classId
     * @return
     */
    JsonNode getAllChapters(String classId) throws JsonProcessingException;

    String redoWork(XxtChapterWork xxtChapterWork, String courseId, String classId, String accountId) throws JsonProcessingException;

    String addTime(XxtChapterWork xxtChapterWork, String courseId, String classId, String accountId,String time) throws JsonProcessingException;
}
