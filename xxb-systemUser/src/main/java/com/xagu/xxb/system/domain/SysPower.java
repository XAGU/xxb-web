package com.xagu.xxb.system.domain;

import com.xagu.xxb.common.web.base.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper=true)
public class SysPower extends BaseDomain {

    public interface Default {
    }

    public interface Update {
    }

    public interface Insert {
    }


    @NotEmpty(message = "权限Id不能为空",groups = Update.class)
    private String powerId;

    @NotEmpty(message = "权限名不能为空",groups = Insert.class)
    private String powerName;

    @NotEmpty(message = "权限类型不能为空",groups = Insert.class)
    private String powerType;

    private String powerCode;

    private String powerUrl;

    private String openType;

    @NotEmpty(message = "父级权限不能为空",groups = Insert.class)
    private String parentId;

    @NotEmpty(message = "图标不能为空",groups = Insert.class)
    private String icon;

    @NotNull(message = "默认排序不能为空",groups = Insert.class)
    private Integer sort;

    private String enable;

    /**
     * 计算列 提供给前端组件
     */
    private String checkArr = "0";
}