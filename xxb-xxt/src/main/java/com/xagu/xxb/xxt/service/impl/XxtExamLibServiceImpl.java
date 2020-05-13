package com.xagu.xxb.xxt.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xagu.xxb.common.tools.AesUtil;
import com.xagu.xxb.xxt.domain.XxtExamLib;
import com.xagu.xxb.xxt.service.XxtExamLibService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xagu
 * Created on 2020/5/13
 * Email:xagu_qc@foxmail.com
 * Describe: 试卷库
 */
@Service("xxtExamLibService")
public class XxtExamLibServiceImpl extends XxtBaseService implements XxtExamLibService {

    private final String XXT_EXAM_LIB = "https://mooc1-1.chaoxing.com/exam/reVerSionPaperList";
    private final String XXT_COURSE_INFO = "https://mooc1-1.chaoxing.com/mycourse/teachercourse";
    private final String XXT_EXAM_DOWNLOAD = "https://mooc-import-export-ans.chaoxing.com/export-paperlibrary";

    @Override
    public List<XxtExamLib> getExamLib(String courseId, String classId, String accountId) throws JsonProcessingException {
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
        builder = UriComponentsBuilder.fromHttpUrl(XXT_EXAM_LIB);
        builder.queryParam("courseId", courseId);
        builder.queryParam("classId", classId);
        responseEntity = restTemplate.exchange(builder.build().toString(), HttpMethod.GET, request, String.class);
        return analysisExamLibInfo(responseEntity.getBody(), courseId, classId);
    }

    private List<XxtExamLib> analysisExamLibInfo(String body, String courseId, String classId) {
        Document html = Jsoup.parse(body);
        String enc = html.select("#importExportEnc").attr("value");
        String cpi = html.select("#cpi").attr("value");
        Elements elements = html.select(".SJList > #tableId > tr");
        List<XxtExamLib> xxtExamLibs = new ArrayList<>();
        for (Element element : elements) {
            XxtExamLib xxtExamLib = new XxtExamLib();
            String paperId = element.select("td:nth-child(1) > input").attr("value");
            xxtExamLib.setTitle(element.select("td:nth-child(2)").attr("title"));
            xxtExamLib.setQuestionNum(element.select("td:nth-child(3)").text());
            xxtExamLib.setDiff(element.select("td:nth-child(4)").text());
            xxtExamLib.setAuthor(element.select("td:nth-child(5)").text());
            xxtExamLib.setCreateTime(element.select("td:nth-child(6)").text());
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(XXT_EXAM_DOWNLOAD);
            builder.queryParam("courseId", courseId);
            builder.queryParam("classId", classId);
            builder.queryParam("paperIds", paperId);
            builder.queryParam("exportType", "word");
            builder.queryParam("cpi", cpi);
            builder.queryParam("exportAll", "false");
            builder.queryParam("enc", enc);
            xxtExamLib.setDownloadUrl(AesUtil.encrypt(builder.toUriString(), AesUtil.KEY_DOWNLOAD_EXAM));
            xxtExamLibs.add(xxtExamLib);
        }
        return xxtExamLibs;
    }
}
