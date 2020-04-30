package com.xagu.xxb.xxt.domain;

import com.xagu.xxb.common.web.base.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserXxtAccount extends BaseDomain {
    private String id;

    private String sysUserId;

    private String xxtAccountId;
}