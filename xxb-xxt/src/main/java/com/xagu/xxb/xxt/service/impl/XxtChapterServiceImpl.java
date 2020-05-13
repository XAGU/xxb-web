package com.xagu.xxb.xxt.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xagu.xxb.common.tools.UrlUtil;
import com.xagu.xxb.xxt.domain.XxtChapterWork;
import com.xagu.xxb.xxt.exception.XxtException;
import com.xagu.xxb.xxt.service.XxtChapterService;
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
import java.util.List;

/**
 * @author xagu
 * Created on 2020/5/10
 * Email:xagu_qc@foxmail.com
 * Describe: 章节相关实现类
 */
@Service("xxtChapterService")
public class XxtChapterServiceImpl extends XxtBaseService implements XxtChapterService {

    //private final String XXT_CHAPTER_LIST = "http://mooc1-api.chaoxing.com/gas/clazz?fields=id,name,state,isstart,isfiled,chatid,bbsid,studentcount,invitecode,course.fields(id,name,imageurl,teacherfactor,mappingcourseid,bulletformat,knowledge.fields(id,parentnodeid,indexorder,label,layer,name,begintime,createtime,lastmodifytime,status,jobUnfinishedCount,clickcount,openlock,card.fields(id,knowledgeid,title,knowledgeTitile,description,cardorder)).type(workid))";
    private final String XXT_CHAPTER_LIST = "http://mooc1-api.chaoxing.com/gas/clazz?fields=course.fields(id,knowledge.fields(id,parentnodeid,indexorder,status,label,layer,name,card.fields(id,knowledgeid,cardorder,title,knowledgeTitile,description)))";
    private final String XXT_CHAPTER_OLDWORK = "https://mooc1-1.chaoxing.com/knowledge/cards";
    private final String XXT_CHAPTER_NEWWORK = "https://mooc1-1.chaoxing.com/workHandle/handle";
    private static final String HOMEWORK_REDO_URL = "http://mooc1-api.chaoxing.com/work/phone/reWork";
    private static final String HOMEWORK_ADDTIME_URL = "https://mooc1-1.chaoxing.com/work/add-time";

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public JsonNode getAllChapters(String classId) throws JsonProcessingException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(XXT_CHAPTER_LIST);
        builder.queryParam("id", classId);
        ResponseEntity<String> responseEntity = restTemplate.exchange(builder.build().toString(), HttpMethod.GET, null, String.class);
        return objectMapper.readTree(responseEntity.getBody()).get("data");
    }

    @Override
    public String redoWork(XxtChapterWork xxtChapterWork, String courseId, String classId, String accountId) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        List<String> cookies = getAccountCookie(accountId);
        httpHeaders.put(HttpHeaders.COOKIE, cookies);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null, httpHeaders);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(XXT_CHAPTER_OLDWORK);
        builder.queryParam("courseid", courseId);
        builder.queryParam("clazzid", classId);
        builder.queryParam("knowledgeid", xxtChapterWork.getKnowledgeid());
        builder.queryParam("num", xxtChapterWork.getCardOrder());
        ResponseEntity<String> response = restTemplate.exchange(builder.build().toString(), HttpMethod.GET, request, String.class);
        String enc = getChapterWorkEnc(response.getBody(), xxtChapterWork).split(",")[0];
        //不重定向的resttemplate
        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory() {
            @Override
            protected void prepareConnection(HttpURLConnection connection, String httpMethod) {
                connection.setInstanceFollowRedirects(false);
            }
        });
        builder = UriComponentsBuilder.fromHttpUrl(XXT_CHAPTER_NEWWORK);
        builder.queryParam("courseid", courseId);
        builder.queryParam("classId", classId);
        builder.queryParam("knowledgeid", xxtChapterWork.getKnowledgeid());
        builder.queryParam("workId", xxtChapterWork.getWorkid());
        builder.queryParam("jobid", xxtChapterWork.getJobid());
        builder.queryParam("enc", enc);
        builder.queryParam("ut", "s");
        builder.queryParam("isphone", "false");
        URI uri = restTemplate.exchange(builder.build().toString(), HttpMethod.GET, request, String.class).getHeaders().getLocation();
        String workAnswerId = UrlUtil.getParamByUrl(uri.toString(), "workAnswerId");
        if (workAnswerId == null) {
            throw new XxtException(500, "章节测验未完成，无法打回");
        }
        String workId = UrlUtil.getParamByUrl(uri.toString(), "workId");
        List<String> teacherRoleCookie = getTeacherRoleCookie(cookies, courseId, classId);
        httpHeaders.remove(HttpHeaders.COOKIE);
        httpHeaders.put(HttpHeaders.COOKIE, teacherRoleCookie);
        request = new HttpEntity<MultiValueMap<String, String>>(null, httpHeaders);
        builder = UriComponentsBuilder.fromHttpUrl(HOMEWORK_REDO_URL);
        builder.queryParam("courseId", courseId);
        builder.queryParam("classId", classId);
        builder.queryParam("workRelationId", workId);
        builder.queryParam("relationAnswerId", workAnswerId);
        builder.queryParam("backReason", "");
        builder.queryParam("studentId", "111");
        response = restTemplate.exchange(builder.build().toString(), HttpMethod.GET, request, String.class);
        JsonNode readTree = objectMapper.readTree(response.getBody());
        //打回成功
        String msg = readTree.get("msg").asText();
        if ("作业打回成功!".equals(msg)) {
            return "章节测验" + msg;
        } else {
            throw new XxtException(500, msg);
        }
    }

    @Override
    public String addTime(XxtChapterWork xxtChapterWork, String courseId, String classId, String accountId, String time) throws JsonProcessingException {
        HttpHeaders httpHeaders = new HttpHeaders();
        List<String> cookies = getAccountCookie(accountId);
        httpHeaders.put(HttpHeaders.COOKIE, cookies);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null, httpHeaders);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(XXT_CHAPTER_OLDWORK);
        builder.queryParam("courseid", courseId);
        builder.queryParam("clazzid", classId);
        builder.queryParam("knowledgeid", xxtChapterWork.getKnowledgeid());
        builder.queryParam("num", xxtChapterWork.getCardOrder());
        ResponseEntity<String> response = restTemplate.exchange(builder.build().toString(), HttpMethod.GET, request, String.class);
        String[] split = getChapterWorkEnc(response.getBody(), xxtChapterWork).split(",");
        String enc = split[0];
        String cpi = split[1];
        //不重定向的resttemplate
        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory() {
            @Override
            protected void prepareConnection(HttpURLConnection connection, String httpMethod) {
                connection.setInstanceFollowRedirects(false);
            }
        });
        builder = UriComponentsBuilder.fromHttpUrl(XXT_CHAPTER_NEWWORK);
        builder.queryParam("courseid", courseId);
        builder.queryParam("classId", classId);
        builder.queryParam("knowledgeid", xxtChapterWork.getKnowledgeid());
        builder.queryParam("workId", xxtChapterWork.getWorkid());
        builder.queryParam("jobid", xxtChapterWork.getJobid());
        builder.queryParam("enc", enc);
        builder.queryParam("ut", "s");
        builder.queryParam("isphone", "false");
        URI uri = restTemplate.exchange(builder.build().toString(), HttpMethod.GET, request, String.class).getHeaders().getLocation();
        String workId = UrlUtil.getParamByUrl(uri.toString(), "workId");
        List<String> teacherRoleCookie = getTeacherRoleCookie(cookies, courseId, classId);
        httpHeaders.remove(HttpHeaders.COOKIE);
        httpHeaders.put(HttpHeaders.COOKIE, teacherRoleCookie);
        request = new HttpEntity<MultiValueMap<String, String>>(null, httpHeaders);
        builder = UriComponentsBuilder.fromHttpUrl(HOMEWORK_ADDTIME_URL);
        builder.queryParam("ids", cpi);
        builder.queryParam("courseId", courseId);
        builder.queryParam("classId", classId);
        builder.queryParam("workId", workId);
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

    private String getChapterWorkEnc(String body, XxtChapterWork xxtChapterWork) throws JsonProcessingException {
        String attachments = body.substring(body.indexOf("{\"attachments\""), body.indexOf(";\n}catch(e){"));
        JsonNode jsonNode = objectMapper.readTree(attachments);
        for (JsonNode node : jsonNode.get("attachments")) {
            if (node.get("property").has("workid") && node.get("property").get("workid").asText().equals(xxtChapterWork.getWorkid())) {
                String cpi = node.get("otherInfo").asText().split("_")[2];
                return node.get("enc").asText() + "," + cpi;
            }
        }
        return "";
    }
}
