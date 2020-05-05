package com.xagu.xxb.common.web.domain;

import com.xagu.xxb.common.web.base.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(callSuper=true)
public class SysUser extends BaseDomain {

    public interface Update {
    }

    public interface Insert {
    }

    /**
    * 编号
    */
    @NotEmpty(message = "用户Id不能为空",groups = SysUser.Update.class)
    private String userId;

    /**
    * 账户
    */
    @NotEmpty(message = "用户名不能为空",groups = SysUser.Insert.class)
    private String username;

    /**
    * 密码
    */
    @NotEmpty(message = "，密码不能为空",groups = SysUser.Insert.class)
    private String password;

    /**
    * 姓名
    */
    private String salt;

    /**
    * 状态
    */
    private String status;

    /**
    * 姓名
    */
    private String realName;

    /**
    * 邮箱
    */
    @Email(message = "邮箱格式不正确",groups = SysUser.Insert.class)
    private String email;

    /**
    * 头像
    */
    private String avatar;

    /**
    * 性别
    */
    private String sex;

    /**
    * 电话
    */
    private String phone;

    /**
    * 是否启用
    */
    private String enable;

    private String login;

    private String roleIds;
}