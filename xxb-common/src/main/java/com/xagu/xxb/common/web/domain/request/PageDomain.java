package com.xagu.xxb.common.web.domain.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Describe: 分 页 参 数 封 装
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Data
public class PageDomain {

    /**
     * 当前页
     * */
    @Min(value = 0,message = "当前页需大于0")
    private Integer page;

    /**
     * 每页数量
     * */
    @Min(0)
    @Min(value = 0,message = "每页数量需大于0")
    private Integer limit;

}
