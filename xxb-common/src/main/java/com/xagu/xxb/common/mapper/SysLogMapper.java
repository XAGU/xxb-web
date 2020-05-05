package com.xagu.xxb.common.mapper;

import com.xagu.xxb.common.web.domain.SysLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xagu
 */
@Mapper
public interface SysLogMapper {
    int insert(SysLog record);
}