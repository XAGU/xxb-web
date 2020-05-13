package com.xagu.xxb.xxt.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author xagu
 * Created on 2020/5/12
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
@Component
public class FakeIpInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        HttpHeaders headers = request.getHeaders();

        // 伪造IP地址
        headers.add("x-forwarded-for", "1.1.1.1");
        headers.add("Proxy-Client-IP", "1.1.1.1");
        headers.add("WL-Proxy-Client-IP", "1.1.1.1");
        headers.add(HttpHeaders.ORIGIN, "https://mooc1-1.chaoxing.com");
        // 保证请求继续被执行
        return execution.execute(request, body);
    }
}