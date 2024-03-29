package com.xagu.xxb.xxt.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xagu.xxb.xxt.domain.XxtCourse;
import com.xagu.xxb.xxt.service.XxtCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xagu
 * Created on 2020/4/30
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
@Service("xxtCourseService")
public class XxtCourseServiceImpl extends XxtBaseService implements XxtCourseService{

    private final static String COURSE_URL = "http://mooc1-api.chaoxing.com/mycourse/backclazzdata?view=json&rss=1";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public List<XxtCourse> getAllCourse(String accountId) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        List<String> cookies = getAccountCookie(accountId);
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
