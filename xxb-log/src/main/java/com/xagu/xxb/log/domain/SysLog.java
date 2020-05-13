package com.xagu.xxb.log.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xagu.xxb.common.web.base.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=true)
public class SysLog extends BaseDomain {
    private String logId;

    private String username;

    private String operaevent;

    private String ua;

    private String clientip;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operatime;

    private Boolean issuccess;
}