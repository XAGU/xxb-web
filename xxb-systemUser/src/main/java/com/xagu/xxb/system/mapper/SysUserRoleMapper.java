package com.xagu.xxb.system.mapper;
import org.apache.ibatis.annotations.Param;

import com.xagu.xxb.system.domain.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserRoleMapper {

    int batchInsert(List<SysUserRole> sysUserRoles);


    List<SysUserRole> selectByUserId(@Param("userId")String userId);


    Integer countByRoleId(@Param("roleId")String roleId);



    int deleteByUserId(String id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
}