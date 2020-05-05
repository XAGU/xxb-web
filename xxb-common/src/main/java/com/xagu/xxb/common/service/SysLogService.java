package com.xagu.xxb.common.service;

import com.xagu.xxb.common.web.domain.SysLog;

/**
 * @author xagu
 * Created on 2020/5/4
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
public interface SysLogService {

    /**
     * 插入一条日志
     * @param sysLog
     * @return
     */
    boolean insert(SysLog sysLog);
}
