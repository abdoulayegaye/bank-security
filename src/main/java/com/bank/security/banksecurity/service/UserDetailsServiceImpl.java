package com.bank.security.banksecurity.service;

import com.bank.security.banksecurity.entity.AppUserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    private UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            AppUserEntity appUser = userService.findByUsername(username);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            appUser.getRoles().forEach(r->{
                authorities.add(new SimpleGrantedAuthority(r.getName()));
            });

            return new User(appUser.getUsername(), appUser.getPassword(), authorities);

    }
}
