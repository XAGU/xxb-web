package com.xagu.xxb.system.domain;

import java.util.List;

/**
 * @author xagu
 * Created on 2020/4/18
 * Email:xagu_qc@foxmail.com
 * Describe: 用户及用户的所有资源权限
 */
public class SysUserAndPowers extends SysUser{

    List<SysPower> powers;

    public List<SysPower> getPowers() {
        return powers;
    }

    public void setPowers(List<SysPower> powers) {
        this.powers = powers;
    }
}
