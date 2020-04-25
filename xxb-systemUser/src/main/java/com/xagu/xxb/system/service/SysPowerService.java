package com.xagu.xxb.system.service;

import com.xagu.xxb.system.domain.SysPower;

import java.util.List;

public interface SysPowerService {

    /**
     * Describe: 根据条件查询权限列表数据
     * Param: SysPower
     * Return: 返回用户列表数据
     * */
    List<SysPower> list();

    /**
     * Describe: 保存权限数据
     * Param: SysPower
     * Return: 操作结果
     * */
    boolean save(SysPower sysPower);

    SysPower getById(String id);

    boolean update(SysPower sysPower);


    /**
     * Describe: 根据 id 删除用户数据
     * Param: id
     * Return: 操作结果
     * */
    boolean remove(String id);

    boolean batchRemove(String[] split);
}
