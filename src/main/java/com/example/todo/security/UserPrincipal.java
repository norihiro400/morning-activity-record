package com.example.todo.security;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.todo.service.login.UserEntity;

public class UserPrincipal implements UserDetails{
    private UserEntity user;

    public UserPrincipal(UserEntity user){
        this.user = user;
    }
    //ユーザーに与えられる権限を返す。全員に"USER"という権限を与える
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }
    //UserEntityオブジェクトのpasswordを返す
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    //UserEntityオブジェクトのusernameを返す
    @Override
    public String getUsername() {
        return user.getUsername();
    }
    // アカウントが有効期限切れでないことを示すために、常にtrueを返します。
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // アカウントがロックされていないことを示すために、常にtrueを返します。
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 資格情報（ここではパスワード）が有効期限切れでないことを示すために、常にtrueを返します。
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // アカウントが有効であることを示すために、常にtrueを返します。
    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
