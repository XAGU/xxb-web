package com.xagu.xxb.xxt.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xagu.xxb.common.tools.StringUtils;
import com.xagu.xxb.common.tools.security.SecurityUtil;
import com.xagu.xxb.system.domain.SysUser;
import com.xagu.xxb.xxt.domain.XxtAccount;
import com.xagu.xxb.xxt.exception.XxtException;
import com.xagu.xxb.xxt.mapper.XxtAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xagu
 * Created on 2020/5/9
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
public class XxtBaseService {

    private final static String GET_TEACHERS_URL = "https://mooc1-api.chaoxing.com:443/teachingClassPhoneManage/phone/getallteacher_stu";

    @Resource
    XxtAccountMapper xxtAccountMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RestTemplate restTemplate;

    public List<String> getAccountCookie(String accountId) throws JsonProcessingException {
        XxtAccount xxtAccount;
        if (StringUtils.isEmpty(accountId)) {
            xxtAccount = xxtAccountMapper.selectAccountByUserId(((SysUser) SecurityUtil.getLoginUser()).getUserId(), null).get(0);
        } else {
            xxtAccount = xxtAccountMapper.selectByAccountIdAndUserId(accountId, ((SysUser) SecurityUtil.getLoginUser()).getUserId());
        }
        if (xxtAccount == null) {
            throw new XxtException(500, "账户不存在");
        }
        JavaType t = objectMapper.getTypeFactory().constructParametricType(List.class, String.class);
        return objectMapper.<ArrayList<String>>readValue(xxtAccount.getCookie(), t);
    }

    protected List<String> getTeacherRoleCookie(List<String> cookies, String courseId, String classId) {
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
