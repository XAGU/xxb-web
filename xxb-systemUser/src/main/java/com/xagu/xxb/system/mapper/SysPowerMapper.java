package com.xagu.xxb.system.mapper;

import com.xagu.xxb.common.web.domain.ResuMenu;
import com.xagu.xxb.system.domain.SysPower;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface SysPowerMapper {
    /**
     * Describe: 根据 SysPower 条件查询权限
     * Param: SysPower
     * Return: SysPower
     * */
    List<SysPower> selectList();

    Integer insert(SysPower sysPower);

    /**
     * Describe: 根据 Id 查询权限
     * Param: id
     * Return: SysPower
     * */
    SysPower selectById(@Param("id") String id);

    List<SysPower> selectByUsername(String username);


    int updateById(SysPower sysPower);

    int deleteById(String id);

    List<SysPower> selectByRoleId(String roleId);

    int batchDelete(String[] split);
}