package com.xagu.xxb.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xagu.xxb.common.tools.serial.SnowFlake;
import com.xagu.xxb.common.web.domain.request.PageDomain;
import com.xagu.xxb.system.domain.SysPower;
import com.xagu.xxb.system.domain.SysRole;
import com.xagu.xxb.system.domain.SysRolePower;
import com.xagu.xxb.system.domain.SysUserRole;
import com.xagu.xxb.system.mapper.SysPowerMapper;
import com.xagu.xxb.system.mapper.SysRoleMapper;
import com.xagu.xxb.system.mapper.SysRolePowerMapper;
import com.xagu.xxb.system.mapper.SysUserRoleMapper;
import com.xagu.xxb.system.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysPowerMapper sysPowerMapper;

    @Resource
    private SysRolePowerMapper sysRolePowerMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysRole> selectAll(SysRole sysRole) {
        return sysRoleMapper.selectList(sysRole);
    }

    @Override
    public PageInfo<SysRole> pageSelect(SysRole sysRole, PageDomain pageDomain) {
        PageHelper.startPage(pageDomain.getPage(), pageDomain.getLimit());
        List<SysRole> list = sysRoleMapper.selectList(sysRole);
        return new PageInfo<>(list);
    }

    /**
     * Describe: 保存角色数据
     * Param: SysRole
     * Return: 操作结果
     */
    @Override
    public boolean save(SysRole sysRole) {
        int result = sysRoleMapper.insert(sysRole);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Describe: 根据 ID 查询角色
     * Param: id
     * Return: 返回角色信息
     */
    @Override
    public SysRole getById(String id) {
        return sysRoleMapper.selectById(id);
    }

    /**
     * Describe: 修改用户数据
     * Param: SysUser
     * Return: 操作结果
     */
    @Override
    public boolean update(SysRole sysRole) {
        Integer result = sysRoleMapper.updateByIdSelective(sysRole);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Describe: 查询角色权限信息
     * Param: id
     * Return: 返回角色信息
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysPower> getRolePower(String roleId) {
        List<SysPower> allPower = sysPowerMapper.selectList();
        List<SysRolePower> myPower = sysRolePowerMapper.selectByRoleId(roleId);
        allPower.forEach(sysPower -> {
            myPower.forEach(sysRolePower -> {
                if (sysRolePower.getPowerId().equals(sysPower.getPowerId())) {
                    sysPower.setCheckArr("1");
                }
            });
        });
        return allPower;
    }

    /**
     * Describe: 保存角色权限数据
     * Param: roleId powerIds
     * Return: 执行结果
     */
    @Override
    @Transactional
    public Boolean saveRolePower(String roleId, List<String> powerIds) {
        sysRolePowerMapper.deleteByRoleId(roleId);
        List<SysRolePower> rolePowers = new ArrayList<>();
        SnowFlake snowFlake = new SnowFlake();
        powerIds.forEach(powerId -> {
            SysRolePower sysRolePower = new SysRolePower();
            sysRolePower.setId("" + snowFlake.nextId());
            sysRolePower.setRoleId(roleId);
            sysRolePower.setPowerId(powerId);
            rolePowers.add(sysRolePower);
        });
        int result = sysRolePowerMapper.batchInsert(rolePowers);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Describe: 根据 id 删除用户数据
     * Param: id
     * Return: Boolean
     */
    @Override
    public Boolean remove(String id) {
        int result = sysRoleMapper.deleteById(id);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean batchRemove(String[] ids) {
        if (sysUserRoleMapper.countByRoleId(ids[0])!=0) {
            return false;
        }
        int result = sysRoleMapper.batchRemove(ids);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

}
