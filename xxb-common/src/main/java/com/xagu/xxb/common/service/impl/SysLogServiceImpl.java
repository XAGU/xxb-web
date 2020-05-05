package com.xagu.xxb.common.service.impl;

import com.xagu.xxb.common.web.domain.SysLog;
import com.xagu.xxb.common.mapper.SysLogMapper;
import com.xagu.xxb.common.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xagu
 * Created on 2020/5/4
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    SysLogMapper sysLogMapper;

    @Override
    public boolean insert(SysLog sysLog) {
        return sysLogMapper.insert(sysLog) > 0;
    }
}
