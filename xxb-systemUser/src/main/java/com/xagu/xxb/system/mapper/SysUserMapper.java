package com.xagu.xxb.system.mapper;

import com.xagu.xxb.system.domain.SysUser;
import com.xagu.xxb.system.domain.SysUserAndPowers;
import com.xagu.xxb.system.domain.SysUserJoinSysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper {

    /**
     * Describe: 根据 username 查询用户
     * Param: username
     * Return: SysUser
     * */
    SysUser selectByUsername(@Param("username") String username);

    /**
     * Describe: 根据 Id 查询用户
     * Param: id
     * Return: SysUser
     * */
    SysUser selectByUserId(String userId);

    /**
     * Describe: 根据 SysUser 条件查询用户
     * Param: username
     * Return: SysUser
     * */
    List<SysUser> selectByAll(SysUser sysUser);


    List<SysUser> selectAll();



    /**
     * Describe: 统计数量
     * Param: username
     * Return: SysUser
     * */
    Integer count();



    /**
     * Describe: 添加用户数据
     * Param: username
     * Return: SysUser
     * */
    Integer insert(SysUser sysUser);

    /**
     * 插入传入有的字段
     * @param record
     * @return
     */
    Integer insertSelective(SysUser record);

    /**
     * 根据 Id 修改用户
     * @param sysUser 用户 id
     * @return
     */
    int updateByUserId(SysUser sysUser);

    /**
     * 根据 Id 修改用户，有就修改，没有就不变
     * @param sysUser
     * @return
     */
    int updateByUserIdSelective(SysUser sysUser);

    /**
     * Describe: 根据 Id 删除用户
     * Param: userId
     * Return: SysUser
     * */
    Integer deleteByUserId(String userId);


    /**
     * Describe: 根据 Id 批量删除
     * Param: username
     * Return: SysUser
     * */
    Integer deleteByIds(String[] ids);

    /**
     * 查询所有用户的所有权限
     *
     * @return
     */
    List<SysUserJoinSysRole> selectAllUserAndRoles();

    /**
     * 通过用户名查询该用户的所有可用的权限
     * @param username
     * @return
     */
    SysUserAndPowers selectUserPowersByUsername(@Param("username") String username);
}