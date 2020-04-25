package com.xagu.xxb.security.service;

import com.xagu.xxb.security.domain.SecurityUserDetails;
import com.xagu.xxb.system.domain.SysUser;
import com.xagu.xxb.system.domain.SysUserAndPowers;
import com.xagu.xxb.system.mapper.SysUserMapper;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SecurityUserDetailsService implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        SysUserAndPowers sysUser = sysUserMapper.selectUserPowersByUsername(s);

/*        if (sysUser == null) {
            throw new UsernameNotFoundException("Account Not");
        } else if ("1".equals(sysUser.getEnable())) {
            throw new LockedException("Account be Banned");
        }*/
        SecurityUserDetails securityUserDetails = new SecurityUserDetails();
        securityUserDetails.setLogin(sysUser.getLogin());
        securityUserDetails.setEnable(sysUser.getEnable());
        securityUserDetails.setPassword(sysUser.getPassword());
        securityUserDetails.setUserId(sysUser.getUserId());
        securityUserDetails.setAvatar(sysUser.getAvatar());
        securityUserDetails.setEmail(sysUser.getEmail());
        securityUserDetails.setRealName(sysUser.getRealName());
        securityUserDetails.setUsername(sysUser.getUsername());
        securityUserDetails.setPowers(sysUser.getPowers());
        return securityUserDetails;
    }
}
