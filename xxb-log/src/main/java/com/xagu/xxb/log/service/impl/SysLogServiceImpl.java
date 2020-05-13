package com.xagu.xxb.log.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xagu.xxb.common.tools.security.SecurityUtil;
import com.xagu.xxb.common.web.domain.request.PageDomain;
import com.xagu.xxb.log.domain.SysLog;
import com.xagu.xxb.log.mapper.SysLogMapper;
import com.xagu.xxb.log.service.SysLogService;
import com.xagu.xxb.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author xagu
 * Created on 2020/5/4
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {

    @Resource
    SysLogMapper sysLogMapper;

    @Override
    public boolean insert(SysLog sysLog) {
        return sysLogMapper.insert(sysLog) > 0;
    }

    @Override
    public PageInfo<SysLog> pageSelectByLoginUser(SysLog sysLog, Date minOperatime, Date maxOperatime, PageDomain pageDomain) {
        SysUser user = (SysUser) SecurityUtil.getLoginUser();
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        List<SysLog> sysLogs = sysLogMapper.selectAllByUsername(user.getUsername(),minOperatime,maxOperatime);
        return new PageInfo<>(sysLogs);
    }

    @Override
    public PageInfo<SysLog> pageSelect(SysLog sysLog, Date minOperatime, Date maxOperatime, PageDomain pageDomain) {
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        List<SysLog> sysLogs = sysLogMapper.selectByAll(sysLog, minOperatime,maxOperatime);
        return new PageInfo<>(sysLogs);
    }
}
