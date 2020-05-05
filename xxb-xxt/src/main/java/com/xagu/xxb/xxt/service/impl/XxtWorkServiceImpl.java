package com.xagu.xxb.xxt.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xagu.xxb.common.tools.security.SecurityUtil;
import com.xagu.xxb.common.web.domain.SysUser;
import com.xagu.xxb.xxt.domain.XxtAccount;
import com.xagu.xxb.xxt.domain.XxtWork;
import com.xagu.xxb.xxt.exception.XxtException;
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

import java.util.*;

/**
 * @author xagu
 * Created on 2020/4/30
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
@Service("xxtWorkService")
public class XxtWorkServiceImpl implements XxtWorkService {


    private final static String ALL_WORK_URL = "https://mobilelearn.chaoxing.com:443/task/getStuWorkAndExamSkipUrl";
    private final static String GET_TEACHERS_URL = "https://mooc1-api.chaoxing.com:443/teachingClassPhoneManage/phone/getallteacher_stu";
    private static final String HOMEWORK_REDO_URL = "http://mooc1-api.chaoxing.com/work/phone/reWork";
    private static final String HOMEWORK_ADDTIME_URL = "https://mooc1-1.chaoxing.com/work/add-time";

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
        httpHeaders.put(HttpHeaders.COOKIE, cookies);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null, httpHeaders);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ALL_WORK_URL);
        builder.queryParam("courseId", courseId);
        builder.queryParam("classId", classId);
        ResponseEntity<String> responseEntity = restTemplate.exchange(builder.build().toString(), HttpMethod.GET, request, String.class);
        return analysisWorkInfo(responseEntity.getBody());
    }

    @Override
    public String redoWork(String url) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        XxtAccount xxtAccount = xxtAccountMapper.selectAccountByUserId(((SysUser) SecurityUtil.getLoginUser()).getUserId(), null).get(0);
        JavaType t = objectMapper.getTypeFactory().constructParametricType(List.class, String.class);
        ArrayList<String> cookies = objectMapper.readValue(xxtAccount.getCookie(), t);
        httpHeaders.put(HttpHeaders.COOKIE, cookies);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        Map<String, String> params = analysisRedoVar(responseEntity.getBody());
        List<String> teacherRoleCookie = getTeacherRoleCookie(cookies, params.get("courseId"), params.get("classId"));
        httpHeaders.remove(HttpHeaders.COOKIE);
        httpHeaders.put(HttpHeaders.COOKIE, teacherRoleCookie);
        request = new HttpEntity<MultiValueMap<String, String>>(null, httpHeaders);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(HOMEWORK_REDO_URL);
        builder.queryParam("courseId", params.get("courseId"));
        builder.queryParam("classId", params.get("classId"));
        builder.queryParam("workRelationId", params.get("workId"));
        builder.queryParam("relationAnswerId", params.get("workAnswerId"));
        builder.queryParam("backReason", "");
        builder.queryParam("studentId", "111");
        ResponseEntity<String> response = restTemplate.exchange(builder.build().toString(), HttpMethod.GET, request, String.class);
        JsonNode readTree = objectMapper.readTree(response.getBody());
        //打回成功
        String msg = readTree.get("msg").asText();
        if ("作业打回成功!".equals(msg)) {
            return msg;
        } else {
            throw new XxtException(500, msg);
        }
    }

    @Override
    public String addTime(String courseId, String clazzId, String taskrefId, String cpi, String time) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        XxtAccount xxtAccount = xxtAccountMapper.selectAccountByUserId(((SysUser) SecurityUtil.getLoginUser()).getUserId(), null).get(0);
        JavaType t = objectMapper.getTypeFactory().constructParametricType(List.class, String.class);
        ArrayList<String> cookies = objectMapper.readValue(xxtAccount.getCookie(), t);
        List<String> teacherRoleCookie = getTeacherRoleCookie(cookies, courseId, clazzId);
        httpHeaders.put(HttpHeaders.COOKIE, teacherRoleCookie);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null, httpHeaders);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(HOMEWORK_ADDTIME_URL);
        builder.queryParam("ids", cpi);
        builder.queryParam("courseId", courseId);
        builder.queryParam("classId", clazzId);
        builder.queryParam("workId", taskrefId);
        builder.queryParam("time", time);
        ResponseEntity<String> responseEntity = restTemplate.exchange(builder.build().toString(), HttpMethod.GET, request, String.class);
        JsonNode readTree = objectMapper.readTree(responseEntity.getBody());
        //打回成功
        String msg = readTree.get("msg").asText();
        if ("操作完成".equals(msg)) {
            return msg;
        } else {
            throw new XxtException(500, msg);
        }
    }

    private Map<String, String> analysisRedoVar(String body) {
        Map<String, String> map = new HashMap<>();
        body = body.substring(body.indexOf("var workId ="), body.indexOf("$(\".cx_alert-txt\").html("));
        body = body.replaceAll("var |\"|\\s", "");
        String[] split = body.split(";");
        for (String s : split) {
            String[] ky = s.split("=");
            if (ky.length == 2) {
                map.put(ky[0], ky[1]);
            } else {
                throw new XxtException(500, "此作业未完成，无法重做");
            }
        }
        return map;
    }

    private List<XxtWork> analysisWorkInfo(String body) {
        Document document = Jsoup.parse(body);
        Elements elements = document.select("#content > ul > li");
        List<XxtWork> xxtWorks = new ArrayList<>();
        for (Element element : elements) {
            XxtWork xxtWork = new XxtWork();
            xxtWork.setWork(element.select("div > p").text());
            xxtWork.setStatus(element.select("div > span").text());
            xxtWork.setWorkUrl(element.attr("data"));
            xxtWork.setCpi(document.select("#cpi").attr("value"));
            xxtWorks.add(xxtWork);
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
