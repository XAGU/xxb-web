package com.xagu.xxb.common.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xagu
 * Created on 2020/4/13
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
public class UrlUtil {
    /**
     * 获取指定url中的某个参数
     * @param url
     * @param name
     * @return
     */
    public static String getParamByUrl(String url, String name) {
        url += "&";
        String pattern = "(\\?|&){1}#{0,1}" + name + "=[a-zA-Z0-9]*(&{1})";

        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(url);
        if (m.find( )) {
            return m.group(0).split("=")[1].replace("&", "");
        } else {
            return null;
        }
    }
}
