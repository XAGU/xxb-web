package com.xagu.xxb.system.service;

import com.github.pagehelper.PageInfo;
import com.xagu.xxb.common.web.domain.ResuMenu;
import com.xagu.xxb.common.web.domain.request.PageDomain;
import com.xagu.xxb.system.domain.SysRole;
import com.xagu.xxb.system.domain.SysUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author xagu
 * Created on 2020/4/20
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
public interface SysUserService {


    /**
     * Describe: 根据条件查询用户列表数据
     * Param: username
     * Return: 返回用户列表数据
     * */
    List<SysUser> selectByAll(SysUser sysUser);

    /**
     * Describe: 根据条件查询用户列表数据  分页
     * Param: username
     * Return: 返回分页用户列表数据
     * */
    PageInfo<SysUser> pageSelect(PageDomain pageDomain);


    SysUser getById(String id);
    /**
     * Describe: 根据 id 删除用户数据
     * Param: id
     * Return: 操作结果
     * */
    boolean remove(String id);


    /**
     * Describe: 根据 id 修改用户数据
     * Param: ids
     * Return: 操作结果
     * */
    boolean update(SysUser sysUser);

    /**
     * Describe: 根据 id 删除用户数据
     * Param: ids
     * Return: 操作结果
     * */
    boolean batchRemove(String[] ids);

    /**
     * Describe: 保存用户数据
     * Param: SysUser
     * Return: 操作结果
     * */
    boolean save(SysUser sysUser);

    /**
     * Describe: 保存用户角色数据
     * Param: SysUser
     * Return: 操作结果
     * */
    boolean saveUserRole(String userId,List<String> roleIds);

    /**
     * Describe: 获取用户角色数据
     * Param: SysUser
     * Return: 操作结果
     * */
    List<SysRole> getUserRole(String userId);

    /**
     * Describe: 获取用户菜单数据
     * Param: SysUser
     * Return: 操作结果
     * */
    List<ResuMenu> selectLoginUserMenus();
}
