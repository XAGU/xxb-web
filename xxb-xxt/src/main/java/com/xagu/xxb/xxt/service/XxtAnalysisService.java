package com.xagu.xxb.xxt.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * @author xagu
 * Created on 2020/5/13
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
public interface XxtAnalysisService {
    String getExportEnc(String courseId, String classId, String accountId) throws JsonProcessingException;

    LinkedHashMap<String,Object> getscoreStatistics(String courseId, String classId, String enc) throws IOException;
}
