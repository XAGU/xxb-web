package com.xagu.xxb.xxt.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xagu
 * Created on 2020/5/10
 * Email:xagu_qc@foxmail.com
 * Describe: 章节
 */
@Data
public class XxtChapterWork implements Serializable {
    /**
     * workid: "20280631d5e44e2ba9e53761143aec29"
     * title: "第1章 课后习题"
     * worktype: "workA"
     * mid: "2329019268601544583610373"
     * _jobid: "work-20280631d5e44e2ba9e53761143aec29"
     * jobid: "work-20280631d5e44e2ba9e53761143aec29"
     * cardOrder: 1
     */

    private String workid;
    private String title;
    private String worktype;
    private String mid;
    private String jobid;
    private String knowledgeid;
    private Integer cardOrder;

}
