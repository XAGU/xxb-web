package com.xagu.xxb.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xagu.xxb.common.tools.security.SecurityUtil;
import com.xagu.xxb.common.tools.serial.SnowFlake;
import com.xagu.xxb.common.web.domain.ResuMenu;
import com.xagu.xxb.common.web.domain.request.PageDomain;
import com.xagu.xxb.system.domain.*;
import com.xagu.xxb.system.mapper.SysRoleMapper;
import com.xagu.xxb.system.mapper.SysUserMapper;
import com.xagu.xxb.system.mapper.SysUserRoleMapper;
import com.xagu.xxb.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xagu
 * Created on 2020/4/20
 * Email:xagu_qc@foxmail.com
 * Describe: TODO
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 查询已登录用户的所有菜单
     * @return
     */
    @Override
    public List<ResuMenu> selectLoginUserMenus() {
        SysUserAndPowers loginUser = (SysUserAndPowers) SecurityUtil.getLoginUser();
        if (loginUser == null || loginUser.getPowers() == null) {
            return null;
        }
        List<SysPower> powers = loginUser.getPowers();
        List<ResuMenu> resuMenus = new ArrayList<>();
        for (SysPower power : powers) {
            ResuMenu resuMenu = new ResuMenu();
            resuMenu.setId(power.getPowerId());
            resuMenu.setTitle(power.getPowerName());
            resuMenu.setHref(power.getPowerUrl());
            resuMenu.setIcon(power.getIcon());
            resuMenu.setType(power.getPowerType());
            resuMenu.setParentId(power.getParentId());
            resuMenus.add(resuMenu);
        }
        return resuMenus;
    }

    /**
     * Describe: 根据条件查询用户列表数据
     * Param: username
     * Return: 返回用户列表数据
     * */
    @Override
    public List<SysUser> selectByAll(SysUser sysUser) {
        List<SysUser> sysUsers = sysUserMapper.selectByAll(sysUser);
        return sysUsers;
    }

    /**
     * Describe: 根据条件查询用户列表数据  分页
     * Param: username
     * Return: 返回分页用户列表数据
     * */
    @Override
    public PageInfo<SysUser> pageSelect(PageDomain pageDomain,SysUser sysUser) {
        PageHelper.startPage(pageDomain.getPage(),pageDomain.getLimit());
        List<SysUser> sysUsers = sysUserMapper.selectAll(sysUser);
        return new PageInfo<>(sysUsers);
    }

    /**
     * Describe: 根据 ID 查询用户
     * Param: id
     * Return: 返回用户信息
     * */
    @Override
    public SysUser getById(String id) {
        return sysUserMapper.selectByUserId(id);
    }

    /**
     * Describe: 根据 id 删除用户数据
     * Param: id
     * Return: Boolean
     * */
    @Override
    public boolean remove(String id) {
        int result = sysUserMapper.deleteByUserId(id);
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Describe: 根据 id 批量删除用户数据
     * Param: ids
     * Return: Boolean
     * */
    @Override
    public boolean batchRemove(String[] ids) {
        int result = sysUserMapper.deleteByIds(ids);
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Describe: 保存用户数据
     * Param: SysUser
     * Return: 操作结果
     * */
    @Override
    public boolean save(SysUser sysUser) {
        int result = sysUserMapper.insert(sysUser);
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Describe: 修改用户数据
     * Param: SysUser
     * Return: 操作结果
     * */
    @Override
    public boolean update(SysUser sysUser) {
        Integer result = sysUserMapper.updateByUserIdSelective(sysUser);
        if(result > 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Describe: 保存用户角色数据
     * Param: SysUser
     * Return: 操作结果
     * */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean saveUserRole(String userId, List<String> roleIds) {
        sysUserRoleMapper.deleteByUserId(userId);
        SnowFlake snowFlake = new SnowFlake();
        List<SysUserRole> sysUserRoles = new ArrayList<>();
        roleIds.forEach(roleId->{
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setId(snowFlake.nextId()+"");
            sysUserRole.setRoleId(roleId);
            sysUserRole.setUserId(userId);
            sysUserRoles.add(sysUserRole);
        });
        int i = sysUserRoleMapper.batchInsert(sysUserRoles);
        return i > 0;
    }

    /**
     * Describe: 获取
     * Param: SysUser
     * Return: 操作结果
     * */
    @Override
    @Transactional(readOnly = true)
    public List<SysRole> getUserRole(String userId){
        List<SysRole> allRole = sysRoleMapper.selectList(null);
        List<SysUserRole> myRole = sysUserRoleMapper.selectByUserId(userId);
        allRole.forEach(sysRole->{
            myRole.forEach(sysUserRole->{
                if(sysRole.getRoleId().equals(sysUserRole.getRoleId())) {
                    sysRole.setChecked(true);
                }
            });
        });
        return allRole;
    }
}
