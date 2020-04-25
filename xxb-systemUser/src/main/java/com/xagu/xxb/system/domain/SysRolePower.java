package com.xagu.xxb.system.domain;

import com.xagu.xxb.common.web.base.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(callSuper=true)
public class SysRolePower extends BaseDomain {
    private String id;

    @NotEmpty(message = "角色Id不能为空")
    private String roleId;

    @NotEmpty(message = "权限Id不能为空")
    private String powerId;
}