package com.xagu.xxb.system.domain;

import com.xagu.xxb.common.web.base.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BaseDomain {

    public interface Update {
    }

    public interface Insert {
    }

    @NotEmpty(message = "角色Id不能为空",groups = SysRole.Update.class)
    private String roleId;

    @NotEmpty(message = "角色名称不能为空",groups = SysRole.Insert.class)
    private String roleName;

    @NotEmpty(message = "角色Code不能为空",groups = SysRole.Insert.class)
    private String roleCode;

    private String enable;

    @NotEmpty(message = "角色描述不能为空",groups = SysRole.Insert.class)
    private String details;

    /**
     * 提供前端 显示
     * */
    private boolean checked = false;
}