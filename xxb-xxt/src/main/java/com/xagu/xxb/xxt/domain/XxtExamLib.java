package com.xagu.xxb.xxt.domain;

import lombok.Data;

/**
 * @author xagu
 * Created on 2020/5/13
 * Email:xagu_qc@foxmail.com
 * Describe: 试卷库实体类
 */
@Data
public class XxtExamLib {
    /**
     * 标题
     */
    private String title;
    /**
     * 题量
     */
    private String questionNum;

    /**
     * 难易度
     */
    private String diff;

    /**
     * 作者
     */
    private String author;

    /**
     * 创建时间
     */

    private String createTime;

    /**
     * 下载链接
     */

    private String downloadUrl;
}
