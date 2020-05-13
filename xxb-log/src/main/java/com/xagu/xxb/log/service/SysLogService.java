package com.xagu.xxb.log.service;


import com.github.pagehelper.PageInfo;
import com.xagu.xxb.common.web.domain.request.PageDomain;
import com.xagu.xxb.log.domain.SysLog;

import java.util.Date;

/**
 * @author xagu
 * Created on 2020/5/4
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
public interface SysLogService {

    /**
     * 插入一条日志
     *
     * @param sysLog
     * @return
     */
    boolean insert(SysLog sysLog);

    PageInfo<SysLog> pageSelectByLoginUser(SysLog sysLog, Date minOperatime, Date maxOperatime, PageDomain pageDomain);

    PageInfo<SysLog> pageSelect(SysLog sysLog, Date minOperatime, Date maxOperatime, PageDomain pageDomain);
}
