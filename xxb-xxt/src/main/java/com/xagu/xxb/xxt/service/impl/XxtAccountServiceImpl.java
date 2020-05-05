package com.xagu.xxb.xxt.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xagu.xxb.common.tools.security.SecurityUtil;
import com.xagu.xxb.common.tools.serial.SnowFlake;
import com.xagu.xxb.common.web.domain.request.PageDomain;
import com.xagu.xxb.system.domain.SysRole;
import com.xagu.xxb.common.web.domain.SysUser;
import com.xagu.xxb.system.mapper.SysRoleMapper;
import com.xagu.xxb.xxt.domain.SysUserXxtAccount;
import com.xagu.xxb.xxt.domain.XxtAccount;
import com.xagu.xxb.xxt.exception.XxtException;
import com.xagu.xxb.xxt.mapper.SysUserXxtAccountMapper;
import com.xagu.xxb.xxt.mapper.XxtAccountMapper;
import com.xagu.xxb.xxt.service.XxtAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

/**
 * @author xagu
 * Created on 2020/4/25
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
@Service("xxtAccountService")
public class XxtAccountServiceImpl implements XxtAccountService {

    private final String LOGIN_URL = "http://i.chaoxing.com/vlogin";
    private final String LOGIN_STUDENT_CODE_URL = "https://passport2-api.chaoxing.com/v6/idNumberLogin";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private XxtAccountMapper xxtAccountMapper;

    @Autowired
    private SysUserXxtAccountMapper sysUserXxtAccountMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean bindXxtAccount(XxtAccount account) {
        //拿到当前登录的用户
        SysUser loginUser = (SysUser) SecurityUtil.getLoginUser();
        //拿到用户的用户组
        SysRole sysRoles = sysRoleMapper.selectByUserId(loginUser.getUserId()).get(0);
        List<XxtAccount> xxtAccounts = selectAll(null);
        //如果是普通用户,只能拥有一个学习通账户
        if ("person".equals(sysRoles.getRoleCode()) && !CollectionUtils.isEmpty(xxtAccounts)) {
            throw new XxtException(500, "普通用户只允许绑定一个账户");
        }
        for (XxtAccount xxtAccount : xxtAccounts) {
            if (xxtAccount.getAccountUsername().equals(account.getAccountUsername())) {
                throw new XxtException(500, "XXT账户已存在");
            }
        }
        List<String> cookies;
        if (account.getAccountSchool() == null) {
            cookies = bindXxtAccountByPhoneNum(account);
        } else {
            cookies = bindXxtAccountByStudentCode(account);
        }
        String cookieStr = null;
        try {
            cookieStr = objectMapper.writeValueAsString(cookies);
        } catch (JsonProcessingException e) {
            throw new XxtException(500, "数据转换发生错误");
        }
        account.setCookie(cookieStr);
        //将账户插入
        account.setUpdateTime(new Date());
        xxtAccountMapper.insertOrUpdate(account);
        SysUserXxtAccount sysUserXxtAccount = new SysUserXxtAccount();
        sysUserXxtAccount.setId(new SnowFlake().nextId() + "");
        sysUserXxtAccount.setSysUserId(loginUser.getUserId());
        sysUserXxtAccount.setXxtAccountId(account.getAccountId());
        return sysUserXxtAccountMapper.insert(sysUserXxtAccount) > 0;
    }

    @Override
    public List<XxtAccount> selectAll(XxtAccount xxtAccount) {
        //拿到当前登录的用户
        SysUser loginUser = (SysUser) SecurityUtil.getLoginUser();
        return xxtAccountMapper.selectAccountByUserId(loginUser.getUserId(),xxtAccount);
    }

    @Override
    public PageInfo<XxtAccount> pageSelect(XxtAccount xxtAccount, PageDomain pageDomain) {
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        //拿到当前登录的用户
        SysUser loginUser = (SysUser) SecurityUtil.getLoginUser();
        List<XxtAccount> list = xxtAccountMapper.selectAccountByUserId(loginUser.getUserId(),xxtAccount);
        return new PageInfo<>(list);
    }

    @Override
    public XxtAccount getById(String id) {
        return null;
    }

    @Override
    public boolean update(XxtAccount xxtAccount) {
        return false;
    }

    @Override
    public boolean batchRemove(String[] ids) {
        return xxtAccountMapper.batchRemove(ids)>0;
    }

    private List<String> bindXxtAccountByStudentCode(XxtAccount account) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(LOGIN_STUDENT_CODE_URL);
        //?fid=4110&idNumber=201710159138&pwd=990301cq
        builder.queryParam("fid", account.getAccountSchool());
        builder.queryParam("idNumber", account.getAccountUsername());
        builder.queryParam("pwd", account.getAccountPassword());
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(builder.build().toString(), null, String.class);
        HttpHeaders httpHeaders = responseEntity.getHeaders();
        if (!responseEntity.hasBody()) {
            throw new XxtException(500, "请求XXT服务器时发生错误");
        }
        JsonNode result = null;
        try {
            result = objectMapper.readTree(responseEntity.getBody());
        } catch (JsonProcessingException e) {
            throw new XxtException(500, "数据转换发生错误");
        }
        if (result.has("status")) {
            if (!result.get("status").asBoolean()) {
                //登录失败
                throw new XxtException(200, "绑定失败，学习通学号密码错误");
            }
        } else if (result.has("result")) {
            if (!result.get("result").asBoolean()) {
                //登录失败
                throw new XxtException(200, "绑定失败，学习通学号密码错误");
            }
        }
        return httpHeaders.get("Set-Cookie");
    }

    private List<String> bindXxtAccountByPhoneNum(XxtAccount account) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(LOGIN_URL);
        builder.queryParam("userName", account.getAccountUsername());
        builder.queryParam("passWord", account.getAccountPassword());
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(builder.build().toString(), String.class);
        HttpHeaders httpHeaders = responseEntity.getHeaders();
        if (!responseEntity.hasBody()) {
            throw new XxtException(500, "请求XXT服务器时发生错误");
        }
        if (!"{\"success\":true}".equals(responseEntity.getBody())) {
            throw new XxtException(200, "绑定失败，学习通账户密码错误");
        }
        return httpHeaders.get("Set-Cookie");
    }


}
