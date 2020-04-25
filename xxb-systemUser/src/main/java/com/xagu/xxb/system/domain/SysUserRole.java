package com.xagu.xxb.system.domain;

import com.xagu.xxb.common.web.base.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class SysUserRole extends BaseDomain {
    private String id;

    private String userId;

    private String roleId;
}