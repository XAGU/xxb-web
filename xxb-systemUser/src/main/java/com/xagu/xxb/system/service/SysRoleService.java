package com.xagu.xxb.system.service;

import com.github.pagehelper.PageInfo;
import com.xagu.xxb.common.web.domain.request.PageDomain;
import com.xagu.xxb.system.domain.SysPower;
import com.xagu.xxb.system.domain.SysRole;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SysRoleService {

    public List<SysRole> selectAll(SysRole sysRole);

    public PageInfo<SysRole> pageSelect(SysRole sysRole, PageDomain pageDomain);

    /**
     * Describe: 保存角色数据
     * Param: SysRole
     * Return: 操作结果
     * */
    boolean save(SysRole sysRole);

    SysRole getById(String id);

    /**
     * Describe: 根据 id 修改用户数据
     * Param: ids
     * Return: 操作结果
     * */
    boolean update(SysRole sysRole);


    List<SysPower> getRolePower(String roleId);

    Boolean saveRolePower(String roleId, List<String> powerIds);

    Boolean remove(String id);

    boolean batchRemove(String[] ids);
}
