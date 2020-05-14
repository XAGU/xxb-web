package com.xagu.xxb.xxt.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xagu.xxb.common.tools.excle.ExcelUtils;
import com.xagu.xxb.common.web.base.BaseService;
import com.xagu.xxb.xxt.service.XxtAnalysisService;
import org.jsoup.Jsoup;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author xagu
 * Created on 2020/5/13
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
@Service("xxtAnalysisService")
public class XxtAnalysisServiceImpl extends XxtBaseService implements XxtAnalysisService {

    private final String XXT_ANALYSIS_EXPORT = "https://mooc1-1.chaoxing.com/moocAnalysis/exportProgress";
    private final String XXT_ANALYSIS_DOWNLOAD = "https://fystat-ans.chaoxing.com/api/export";
    private final String XXT_COURSE_INFO = "https://mooc1-1.chaoxing.com/mycourse/teachercourse";


    @Override
    public String getExportEnc(String courseId, String classId, String accountId) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        List<String> cookies = getAccountCookie(accountId);
        List<String> teacherRoleCookie = getTeacherRoleCookie(cookies, courseId, classId);
        httpHeaders.put(HttpHeaders.COOKIE, teacherRoleCookie);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null, httpHeaders);
        //------------想要免enc访问作业库需要先访问一下XXT_COURSE_INFO获取到SETCOOKIE携带发送才能正常访问
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(XXT_COURSE_INFO);
        builder.queryParam("moocId", courseId);
        builder.queryParam("clazzid", classId);
        ResponseEntity<String> responseEntity = restTemplate.exchange(builder.build().toString(), HttpMethod.GET, request, String.class);
        List<String> setCookies = responseEntity.getHeaders().get(HttpHeaders.SET_COOKIE);
        assert setCookies != null;
        teacherRoleCookie.addAll(setCookies);
        //------------------------------------------------------------------------------------------
        request = new HttpEntity<MultiValueMap<String, String>>(null, httpHeaders);
        builder = UriComponentsBuilder.fromHttpUrl(XXT_ANALYSIS_EXPORT);
        builder.queryParam("courseId", courseId);
        builder.queryParam("classId", classId);
        responseEntity = restTemplate.exchange(builder.build().toString(), HttpMethod.GET, request, String.class);
        return analysisExportInfo(responseEntity.getBody(), courseId, classId);
    }

    @Override
    public LinkedHashMap<String,String> getscoreStatistics(String courseId, String classId, String seltables, String enc, String accountId) throws IOException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(XXT_ANALYSIS_DOWNLOAD);
        builder.queryParam("courseId", courseId);
        builder.queryParam("classId", classId);
        builder.queryParam("exportEnc", enc);
        builder.queryParam("seltables", seltables);
        builder.queryParam("sortType", "1");
        ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity(builder.toUriString(), byte[].class);
        byte[] body = responseEntity.getBody();
        return ExcelUtils.excel2json(new ByteArrayInputStream(body));
    }

    private String analysisExportInfo(String body, String courseId, String classId) {
        return Jsoup.parse(body).select("#downForm > input[type=hidden]:nth-child(5)").attr("value");
    }
}
