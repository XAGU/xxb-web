package com.xagu.xxb.xxt.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.xagu.xxb.common.web.base.BaseDomain;
import java.util.Date;

import com.xagu.xxb.common.web.domain.ResuTable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * @author xagu
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class XxtAccount extends BaseDomain {

    public interface JsonWithOutCookie extends ResuTable.JsonResuTable {};

    /**
     * id
     */
    @JsonView(JsonWithOutCookie.class)
    private String accountId;

    /**
     * 学习通登录用户名
     */
    @JsonView(JsonWithOutCookie.class)
    @NotEmpty(message = "用户名不能为空")
    private String accountUsername;

    /**
     * 学习通登录密码
     */
    @JsonView(JsonWithOutCookie.class)
    @NotEmpty(message = "密码不能为空")
    private String accountPassword;

    /**
     * 用户的学校（学号登录时需要）
     */
    @JsonView(JsonWithOutCookie.class)
    private Integer accountSchool;

    /**
     * cookie 15 天更新
     */
    private String cookie;

    /**
     * cookie更新最后的记录
     */
    @JsonView(JsonWithOutCookie.class)
    private Date updateTime;
}