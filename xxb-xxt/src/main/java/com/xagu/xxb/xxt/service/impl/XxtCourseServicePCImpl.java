package com.xagu.xxb.xxt.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xagu.xxb.common.tools.StringUtils;
import com.xagu.xxb.common.tools.security.SecurityUtil;
import com.xagu.xxb.system.domain.SysUser;
import com.xagu.xxb.xxt.domain.XxtAccount;
import com.xagu.xxb.xxt.domain.XxtWork;
import com.xagu.xxb.xxt.mapper.XxtAccountMapper;
import com.xagu.xxb.xxt.service.XxtWorkService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xagu
 * Created on 2020/4/30
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
@Service("xxtCoursePcService")
public class XxtCourseServicePCImpl implements XxtWorkService {

    private final static String BASE_URL = "http://mooc1-1.chaoxing.com";
    private final static String COURSE_URL = "http://mooc1-api.chaoxing.com/mycourse/backclazzdata?view=json&rss=1";
    private final static String COURSE_INFO_URL = "http://mooc1-1.chaoxing.com/mycourse/teachercourse";
    private final static String WORK_URL = "https://mobilelearn.chaoxing.com/task/getTeaWorkAndExamSkipUrl";
    private final static String GET_TEACHERS_URL = "https://mooc1-api.chaoxing.com:443/teachingClassPhoneManage/phone/getallteacher_stu";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private XxtAccountMapper xxtAccountMapper;

    @Override
    public List<XxtWork> getAllWork(String courseId, String classId) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        XxtAccount xxtAccount = xxtAccountMapper.selectAccountByUserId(((SysUser) SecurityUtil.getLoginUser()).getUserId(), null).get(0);
        JavaType t = objectMapper.getTypeFactory().constructParametricType(List.class, String.class);
        ArrayList<String> cookies = objectMapper.readValue(xxtAccount.getCookie(), t);
        List<String> teacherRoleCookie = getTeacherRoleCookie(cookies, courseId, classId);
        httpHeaders.put(HttpHeaders.COOKIE, teacherRoleCookie);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null, httpHeaders);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(COURSE_INFO_URL);
        builder.queryParam("moocId", courseId);
        builder.queryParam("clazzid", classId);
        ResponseEntity<String> responseEntity = restTemplate.exchange(builder.build().toString(), HttpMethod.GET, request, String.class);
        String workUrl = BASE_URL + analysisMyCourseInfo(responseEntity.getBody());
        //workUrl = workUrl.replaceFirst("(?<=classId=)\\d*", classId);
        ResponseEntity<String> entity = restTemplate.exchange(workUrl, HttpMethod.GET, request, String.class);
        return analysisWorkInfo(entity.getBody());
    }

    @Override
    public String redoWork(String url) throws JsonProcessingException {
        return null;
    }



    private String analysisMyCourseInfo(String body) {
        Document document = Jsoup.parse(body);
        Element element = document.select("body > div.header > div.headerwrap > div.menu > div.navshow > ul > li:nth-child(5)").first();
        String WORK_URL = element.select("a").attr("href");
        return WORK_URL;
    }

    private List<XxtWork> analysisWorkInfo(String body) {
        Document document = Jsoup.parse(body);
        Elements elements = document.select("#RightCon > div > div > div.ulDiv > ul > li");
        List<XxtWork> xxtWorks = new ArrayList<>();
        for (Element element : elements) {
            XxtWork xxtWork = new XxtWork();
            xxtWork.setWork(element.select("div.titTxt > p > a").text());
            xxtWork.setStatus(element.select("div.titTxt > a").attr("id").replace("del", ""));
            if (!StringUtils.isEmpty(xxtWork.getStatus())) {
                xxtWorks.add(xxtWork);
            }
        }
        return xxtWorks;
    }

    private List<String> getTeacherRoleCookie(List<String> cookies, String courseId, String classId) {
        //2.获取课程的老师的uid
        List<String> teachers = getAllTeachers(courseId, classId, cookies);
        if (teachers == null || teachers.size() == 0) {
            return null;
        }
        //3.修改cookie
        List<String> teacherCookies = new ArrayList<>();
        Iterator<String> iterator = cookies.iterator();
        while (iterator.hasNext()) {
            String cookie = iterator.next();
            if (cookie.matches("_uid=.*")) {
                String s = cookie.replaceFirst("(?<=_uid=)\\d*", teachers.get(0));
                teacherCookies.add(s);
            } else {
                teacherCookies.add(cookie);
            }
        }
        return teacherCookies;
    }

    private List<String> getAllTeachers(String courseId, String classId, List<String> cookies) {
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        headers.put(HttpHeaders.COOKIE, cookies);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null, headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GET_TEACHERS_URL);
        builder.queryParam("courseId", courseId);
        builder.queryParam("clazzId", classId);
        ResponseEntity<String> responseEntity = restTemplate.exchange(builder.build().toString(), HttpMethod.GET, request, String.class);
        if (responseEntity.hasBody()) {
            String entityBody = responseEntity.getBody();
            try {
                JsonNode readTree = objectMapper.readTree(entityBody);
                if (readTree.get("result").asInt() == 1) {
                    return readTree.findValuesAsText("userId");
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
