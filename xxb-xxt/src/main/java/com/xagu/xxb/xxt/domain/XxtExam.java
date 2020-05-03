package com.xagu.xxb.xxt.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xagu
 * Created on 2020/4/30
 * Email:xagu_qc@foxmail.com
 * Describe: zuoye
 */
@Data
public class XxtExam implements Serializable {
    private String status;
    private String exam;
    private String cpi;
    private String examUrl;
}
