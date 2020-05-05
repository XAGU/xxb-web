package com.xagu.xxb.xxt.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xagu.xxb.common.tools.security.SecurityUtil;
import com.xagu.xxb.common.web.domain.SysUser;
import com.xagu.xxb.xxt.domain.XxtAccount;
import com.xagu.xxb.xxt.domain.XxtExam;
import com.xagu.xxb.xxt.exception.XxtException;
import com.xagu.xxb.xxt.mapper.XxtAccountMapper;
import com.xagu.xxb.xxt.service.XxtExamService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.HttpURLConnection;
import java.net.URI;
import java.util.*;

/**
 * @author xagu
 * Created on 2020/4/30
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
@Service("xxtExamService")
public class XxtExamServiceImpl implements XxtExamService {


    private final static String SKIP_EXAM_URL = "https://mobilelearn.chaoxing.com:443/task/getStuWorkAndExamSkipUrl";
    private final static String ALL_EXAM_URL = "https://mooc1-api.chaoxing.com:443/exam/phone/stutask/examlist";
    private final static String GET_TEACHERS_URL = "https://mooc1-api.chaoxing.com:443/teachingClassPhoneManage/phone/getallteacher_stu";
    private static final String EXAM_REDO_URL = "https://mooc1-api.chaoxing.com:443/exam/phone/reTest";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private XxtAccountMapper xxtAccountMapper;


    @Override
    public List<XxtExam> getAllExam(String courseId, String classId) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        XxtAccount xxtAccount = xxtAccountMapper.selectAccountByUserId(((SysUser) SecurityUtil.getLoginUser()).getUserId(), null).get(0);
        JavaType t = objectMapper.getTypeFactory().constructParametricType(List.class, String.class);
        ArrayList<String> cookies = objectMapper.readValue(xxtAccount.getCookie(), t);
        httpHeaders.put(HttpHeaders.COOKIE, cookies);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null, httpHeaders);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(SKIP_EXAM_URL);
        builder.queryParam("courseId", courseId);
        builder.queryParam("classId", classId);
        URI uri = restTemplate.postForLocation(builder.build().toString(), request);
        ResponseEntity<String> responseEntity = restTemplate.exchange(ALL_EXAM_URL + "?" + uri.getQuery(), HttpMethod.GET, request, String.class);
        return analysisExamInfo(responseEntity.getBody());
    }

    @Override
    public String redoExam(String url) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        XxtAccount xxtAccount = xxtAccountMapper.selectAccountByUserId(((SysUser) SecurityUtil.getLoginUser()).getUserId(), null).get(0);
        JavaType t = objectMapper.getTypeFactory().constructParametricType(List.class, String.class);
        ArrayList<String> cookies = objectMapper.readValue(xxtAccount.getCookie(), t);
        httpHeaders.put(HttpHeaders.COOKIE, cookies);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null, httpHeaders);
        //不重定向的resttemplate
        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory() {
            @Override
            protected void prepareConnection(HttpURLConnection connection, String httpMethod) {
                connection.setInstanceFollowRedirects(false);
            }
        });
        URI uri1 = restTemplate.exchange(url, HttpMethod.GET, request, String.class).getHeaders().getLocation();
        URI uri2 = restTemplate.exchange(uri1, HttpMethod.GET, request, String.class).getHeaders().getLocation();
        if (uri2 == null) {
            throw new XxtException(500, "考试未提交，无法重考！");
        }
        Map<String, String> params = analysisRedoUri(uri2.getQuery());
        List<String> teacherRoleCookie = getTeacherRoleCookie(cookies, params.get("courseId"), params.get("classId"));
        httpHeaders.remove(HttpHeaders.COOKIE);
        httpHeaders.put(HttpHeaders.COOKIE, teacherRoleCookie);
        request = new HttpEntity<MultiValueMap<String, String>>(null, httpHeaders);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(EXAM_REDO_URL);
        builder.queryParam("courseId", params.get("courseId"));
        builder.queryParam("classId", params.get("classId"));
        builder.queryParam("relationId", params.get("examId"));
        builder.queryParam("answerId", params.get("examAnswerId"));
        ResponseEntity<String> response = restTemplate.exchange(builder.build().toString(), HttpMethod.GET, request, String.class);
        JsonNode readTree = objectMapper.readTree(response.getBody());
        //打回成功
        String msg = readTree.get("msg").asText();
        if ("考试打回成功！".equals(msg)) {
            return msg;
        } else {
            throw new XxtException(500, msg);
        }
    }

    private Map<String, String> analysisRedoUri(String query) {
        Map<String, String> map = new HashMap<>();
        String[] split = query.split("&");
        for (String s : split) {
            String[] ky = s.split("=");
            if (ky.length == 2) {
                map.put(ky[0], ky[1]);
            } else {
                throw new XxtException(500, "此考试未完成，无法重做");
            }
        }
        return map;
    }

    private List<XxtExam> analysisExamInfo(String body) {
        Document document = Jsoup.parse(body);
        Elements elements = document.select("#content > ul > li");
        List<XxtExam> xxtExams = new ArrayList<>();
        for (Element element : elements) {
            XxtExam xxtExam = new XxtExam();
            xxtExam.setExam(element.select("div > p").text());
            xxtExam.setStatus(element.select("div > span").text());
            xxtExam.setExamUrl(element.attr("data"));
            xxtExam.setCpi(document.select("#cpi").attr("value"));
            xxtExams.add(xxtExam);
        }
        return xxtExams;
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
