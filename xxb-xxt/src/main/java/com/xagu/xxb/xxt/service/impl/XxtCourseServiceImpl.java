package com.xagu.xxb.xxt.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xagu.xxb.common.tools.StringUtils;
import com.xagu.xxb.common.tools.security.SecurityUtil;
import com.xagu.xxb.system.domain.SysUser;
import com.xagu.xxb.xxt.domain.XxtAccount;
import com.xagu.xxb.xxt.domain.XxtCourse;
import com.xagu.xxb.xxt.domain.XxtWork;
import com.xagu.xxb.xxt.mapper.XxtAccountMapper;
import com.xagu.xxb.xxt.service.XxtCourseService;
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
@Service("xxtCourseService")
public class XxtCourseServiceImpl implements XxtCourseService {

    private final static String COURSE_URL = "http://mooc1-api.chaoxing.com/mycourse/backclazzdata?view=json&rss=1";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private XxtAccountMapper xxtAccountMapper;

    @Override
    public List<XxtCourse> getAllCourse() throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        XxtAccount xxtAccount = xxtAccountMapper.selectAccountByUserId(((SysUser) SecurityUtil.getLoginUser()).getUserId(), null).get(0);
        JavaType t = objectMapper.getTypeFactory().constructParametricType(List.class, String.class);
        ArrayList<String> cookies = objectMapper.readValue(xxtAccount.getCookie(), t);
        httpHeaders.put(HttpHeaders.COOKIE, cookies);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(COURSE_URL, HttpMethod.GET, request, String.class);
        return analysisCourseInfo(responseEntity.getBody());
    }


    /**
     * 解析查询Course返回的json
     *
     * @param response
     */
    private List<XxtCourse> analysisCourseInfo(String response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<XxtCourse> courses = new ArrayList<>();
        JsonNode readTree = objectMapper.readTree(response);
        if (readTree.get("result").asInt() == 0) {
            return null;
        }
        JsonNode channelList = readTree.get("channelList");
        for (int i = 0; i < channelList.size(); i++) {
            if (channelList.get(i).get("cataid").asInt() == 100000002) {
                JsonNode content = channelList.get(i).get("content");
                if (!content.has("course")) {
                    continue;
                }
                XxtCourse course = new XxtCourse();
                if (content.has("id")) {
                    course.setClassId(content.get("id").asText());
                } else {
                    course.setClassId("");
                }
                if (content.has("name")) {
                    course.setClassname(content.get("name").asText());
                } else {
                    course.setClassname("");
                }
                JsonNode courseData = content.findValue("data").get(0);
                if (courseData.has("id")) {
                    course.setCourseId(courseData.get("id").asText());
                } else {
                    course.setCourseId("");
                }
                if (courseData.has("teacherfactor")) {
                    course.setTeacher(courseData.get("teacherfactor").asText());
                } else {
                    course.setTeacher("");
                }
                if (courseData.has("imageurl")) {
                    course.setImageUrl(courseData.get("imageurl").asText());
                } else {
                    course.setImageUrl("");
                }
                if (courseData.has("name")) {
                    course.setName(courseData.get("name").asText());
                } else {
                    course.setName("");
                }
                if (courseData.has("courseSquareUrl")) {
                    String courseSquareUrl = courseData.get("courseSquareUrl").asText();
                    course.setUid(courseSquareUrl.substring(courseSquareUrl.lastIndexOf("/") + 1));
                } else {
                    course.setUid("");
                }
                courses.add(course);
            }
        }
        return courses;
    }
}
