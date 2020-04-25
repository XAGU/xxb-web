package com.xagu.xxb.system.mapper;

import com.xagu.xxb.system.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMapper {


    int insertSelective(SysRole record);


    int updateByIdSelective(SysRole record);


    List<SysRole> selectList(SysRole sysRole);


    /**
     * Describe: 添加角色数据
     * Param: SysRole
     * Return: 执行结果
     * */
    Integer insert(SysRole sysRole);

    /**
     * Describe: 根据 Id 查询角色
     * Param: id
     * Return: SysRole
     * */
    SysRole selectById(@Param("roleId") String roleId);

    /**
     * Describe: 根据 Id 修改角色
     * Param: username
     * Return: SysUser
     * */
    Integer updateById(SysRole sysRole);

    /**
     * Describe: 根据 Id 删除用户租
     * Param: username
     * Return: SysUser
     * */
    Integer deleteById(String id);

    List<SysRole> selectByUserId(String userId);

    int batchRemove(String[] ids);
}