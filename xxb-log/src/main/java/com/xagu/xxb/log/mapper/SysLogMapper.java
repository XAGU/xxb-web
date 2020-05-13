package com.xagu.xxb.log.mapper;
import java.util.Date;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.xagu.xxb.log.domain.SysLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xagu
 */
@Mapper
public interface SysLogMapper {
    int insert(SysLog record);

    List<SysLog> selectAllByUsername(@Param("username")String username,@Param("minOperatime")Date minOperatime,@Param("maxOperatime")Date maxOperatime);


    List<SysLog> selectByAll(SysLog sysLog,@Param("minOperatime")Date minOperatime,@Param("maxOperatime")Date maxOperatime);



}