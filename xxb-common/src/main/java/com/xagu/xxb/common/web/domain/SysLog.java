package com.xagu.xxb.common.web.domain;

import com.xagu.xxb.common.web.base.BaseDomain;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class SysLog extends BaseDomain {
    private String logId;

    private String username;

    private String operaevent;

    private String ua;

    private String clientip;

    private Date operatime;

    private Boolean issuccess;
}