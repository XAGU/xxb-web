package com.xagu.xxb.xxt.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.PageInfo;
import com.xagu.xxb.common.constant.MessageConstants;
import com.xagu.xxb.common.tools.serial.SnowFlake;
import com.xagu.xxb.common.web.base.BaseController;
import com.xagu.xxb.common.web.domain.ResuBean;
import com.xagu.xxb.common.web.domain.ResuTable;
import com.xagu.xxb.common.web.domain.request.PageDomain;
import com.xagu.xxb.xxt.domain.XxtAccount;
import com.xagu.xxb.xxt.service.XxtAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;

/**
 * @author xagu
 * Created on 2020/4/25
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */

@Validated
@RequestMapping("xxt/account")
@RestController
public class XxtAccountController extends BaseController {

    /**
     * Describe: 基础路径
     */
    private static String MODULE_PATH = "xxt/account/";

    /**
     * Describe:
     */
    @Resource
    private XxtAccountService xxtAccountService;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Describe: 获取权限列表视图
     * Param ModelAndView
     * Return 权限列表视图
     */
    @GetMapping("main")
    @PreAuthorize("hasPermission('/xxt/account/select','xxt:account:select')")
    public ModelAndView main(ModelAndView modelAndView) {
        modelAndView.setViewName(MODULE_PATH + "main");
        return modelAndView;
    }

    /**
     * Describe: 获取账户列表数据
     * Param SysRole PageDomain
     * Return 账户列表数据
     */
    @GetMapping
    @JsonView(XxtAccount.JsonWithOutCookie.class)
    @PreAuthorize("hasPermission('/xxt/account/select','xxt:account:select')")
    public ResuTable data(PageDomain pageDomain,XxtAccount xxtAccount) {
        PageInfo<XxtAccount> pageInfo = xxtAccountService.pageSelect(xxtAccount, pageDomain);
        return pageTable(pageInfo.getList(), pageInfo.getTotal());
    }


    /**
     * Describe: 获取账户新增视图
     * Param ModelAndView
     * Return 角色新增视图
     */
    @GetMapping("add")
    @PreAuthorize("hasPermission('/xxt/account/insert','xxt:account:insert')")
    public ModelAndView add(ModelAndView modelAndView) {
        modelAndView.setViewName(MODULE_PATH + "add");
        return modelAndView;
    }

    /**
     * Describe: 绑定学习通信息
     * Param: SysPower
     * Return: ResuBean
     */
    @PostMapping
    @PreAuthorize("hasPermission('/xxt/account/insert','xxt:account:insert')")
    public ResuBean save(@RequestBody XxtAccount xxtAccount) {
        xxtAccount.setAccountId("" + new SnowFlake().nextId());
        boolean result = xxtAccountService.bindXxtAccount(xxtAccount);
        return decide(
                result,
                MessageConstants.BIND_SUCCESS,
                MessageConstants.BIND_FAILURE
        );
    }

    @PostMapping
    @RequestMapping("getOrgList")
    public String searchSchool(String keyword) {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://i.chaoxing.com/getOrgList?keyword=" + keyword, null, String.class);
        return responseEntity.getBody();
    }

    /**
     * Describe: 账户删除接口
     * Param: id
     * Return: ResuBean
     * */
    @DeleteMapping("{ids}")
    @PreAuthorize("hasPermission('/xxt/account/delete','xxt:account:delete')")
    public ResuBean batchRemove(@PathVariable @NotEmpty(message = "账户Id不能为空") String ids){
        boolean result  = xxtAccountService.batchRemove(ids.split(","));
        return decide(
                result,
                MessageConstants.REMOVE_SUCCESS,
                MessageConstants.REMOVE_FAILURE
        );
    }

}

