package com.xagu.xxb.common.web.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Describe: 前端表格数据封装
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */

@Data
@EqualsAndHashCode()
public class ResuTable {

    public interface JsonResuTable{};

    /**
     * 状态码
     * */
    @JsonView(JsonResuTable.class)
    private Integer code;

    /**
     * 提示消息
     * */
    @JsonView(JsonResuTable.class)
    private String msg;

    /**
     * 消息总量
     * */
    @JsonView(JsonResuTable.class)
    private Long count;

    /**
     * 数据对象
     * */
    @JsonView(JsonResuTable.class)
    private Object data;

}
