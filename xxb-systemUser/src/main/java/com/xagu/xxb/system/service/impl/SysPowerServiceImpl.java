package com.xagu.xxb.system.service.impl;

import com.xagu.xxb.system.domain.SysPower;
import com.xagu.xxb.system.mapper.SysPowerMapper;
import com.xagu.xxb.system.service.SysPowerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("sysPowerService")
public class SysPowerServiceImpl implements SysPowerService {

    @Resource
    private SysPowerMapper sysPowerMapper;

    @Override
    public List<SysPower> list() {
        return sysPowerMapper.selectList();
    }

    @Override
    public boolean save(SysPower sysPower) {
        int result =  sysPowerMapper.insert(sysPower);
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Describe: 根据 ID 查询角色
     * Param: id
     * Return: 返回角色信息
     * */
    @Override
    public SysPower getById(String id) {
        return sysPowerMapper.selectById(id);
    }

    @Override
    public boolean update(SysPower sysPower) {
        int result = sysPowerMapper.updateById(sysPower);
        if(result > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean remove(String id) {
        int result = sysPowerMapper.deleteById(id);
        if(result > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean batchRemove(String[] split) {
        int result = sysPowerMapper.batchDelete(split);
        if(result > 0){
            return true;
        }else{
            return false;
        }
    }
}
