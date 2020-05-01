package com.xagu.xxb.xxt.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xagu
 * Created on 2020/4/30
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
@Data
public class XxtCourse implements Serializable {
    private String courseId;
    private String teacher;
    private String imageUrl;
    private String name;
    private String classId;
    private String classname;
    private String uid;
}
