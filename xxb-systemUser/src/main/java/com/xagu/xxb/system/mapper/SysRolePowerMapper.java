package com.xagu.xxb.system.mapper;

import com.xagu.xxb.system.domain.SysRolePower;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRolePowerMapper {
    List<SysRolePower> selectByRoleId(String roleId);

    int batchInsert(List<SysRolePower> sysRolePowers);

    int deleteByRoleId(String roleId);
}