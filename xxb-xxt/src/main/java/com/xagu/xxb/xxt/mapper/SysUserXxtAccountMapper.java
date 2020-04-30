package com.xagu.xxb.xxt.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.xagu.xxb.xxt.domain.SysUserXxtAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserXxtAccountMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUserXxtAccount record);

    int insertOrUpdate(SysUserXxtAccount record);

    int insertOrUpdateSelective(SysUserXxtAccount record);

    int insertSelective(SysUserXxtAccount record);

    SysUserXxtAccount selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserXxtAccount record);

    int updateByPrimaryKey(SysUserXxtAccount record);

    int deleteBySysUserId(@Param("sysUserId") String sysUserId);

    List<String> selectAccountIdBySysUserId(@Param("sysUserId")String sysUserId);




}