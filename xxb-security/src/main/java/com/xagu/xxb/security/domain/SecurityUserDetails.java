package com.xagu.xxb.security.domain;

import com.xagu.xxb.system.domain.SysUser;
import com.xagu.xxb.system.domain.SysUserAndPowers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 实现SpringSecurity类
 * @author xagu
 */
public class SecurityUserDetails extends SysUserAndPowers implements UserDetails{


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return Integer.parseInt(super.getEnable()) == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Integer.parseInt(super.getEnable()) == 0;
    }

}
