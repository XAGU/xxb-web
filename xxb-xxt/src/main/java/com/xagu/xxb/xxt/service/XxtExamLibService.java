package com.xagu.xxb.xxt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xagu.xxb.xxt.domain.XxtExamLib;

import java.util.List;

/**
 * @author xagu
 * Created on 2020/5/13
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
public interface XxtExamLibService {
    List<XxtExamLib> getExamLib(String courseId, String classId, String paperId, String accountId) throws JsonProcessingException;
}
