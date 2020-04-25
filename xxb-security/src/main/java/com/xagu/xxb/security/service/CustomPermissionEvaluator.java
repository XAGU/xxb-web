package com.xagu.xxb.security.service;

import com.xagu.xxb.security.domain.SecurityUserDetails;
import com.xagu.xxb.system.domain.SysPower;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;

/**
 * Describe: 自定义 Security 权限注解实现
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 */
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {


    /**
     * Describe: 自定义 Security 权限认证 @HasPermission
     * Param: Authentication
     * Return Boolean
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        // 根据用户账户查询权限信息
        Object principal = authentication.getPrincipal();

        if (principal instanceof SecurityUserDetails) {
            if ("admin".equals(((SecurityUserDetails) principal).getUsername())) {
                return true;
            }
            // 根据用户账户查询权限信息
            //默认一个用户只有一个用户组，所以get（0）
            List<SysPower> powerList = ((SecurityUserDetails) principal).getPowers();
/*            Map<String, SysPower> powers = new HashMap<>();
            for (SysPower sysPower : powerList) {
                powers.put(sysPower.getPowerCode(), sysPower);
                powers.put(sysPower.getPowerId(),sysPower);
            }
            if (!powers.containsKey(o1)){
                return false;
            }
            while (powers.containsKey(powers.get())
            SysPower power = powers.get(o1);
            if (power != null) {
                //有这个权限，但是我们还是得判断一下他的父节点有没有权限
                while (powers.containsKey(power.get(o1)))
            }*/

            for (SysPower sysPower : powerList) {
                //权限匹配上且权限的enable为0(数据sql查询控制了enable==0)
                if (sysPower.getPowerCode().equals(o1)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }

}
