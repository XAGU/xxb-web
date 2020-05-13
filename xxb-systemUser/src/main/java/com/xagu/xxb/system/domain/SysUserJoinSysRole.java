package com.xagu.xxb.system.domain;

import java.util.List;

/**
 * @author xagu
 */
public class SysUserJoinSysRole extends SysUser {
    private List<SysRole> sysRoleList;

    public List<SysRole> getSysRoleList() {
        return sysRoleList;
    }

    public void setSysRoleList(List<SysRole> sysRoleList) {
        this.sysRoleList = sysRoleList;
    }
}