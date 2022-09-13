package com.bank.security.banksecurity.service;

import com.bank.security.banksecurity.domain.Role;
import com.bank.security.banksecurity.domain.User;
import com.bank.security.banksecurity.repository.RolesRepository;
import com.bank.security.banksecurity.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsImp implements UserDetailsService {
    final UserRepository userRepository;
    final RolesRepository rolesRepository;
    UserDetailsImp(RolesRepository rolesRepository, UserRepository userRepository){
        this.rolesRepository = rolesRepository;
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.userRepository.findByUsername(username);
        System.out.println("USER DETAIL"+ u.getUsername());
        return new org.springframework.security.core.userdetails.User(u.getUsername(),u.getPassword(),
                true,true,true,true,getAuthorities(u.getRoles()));

    }
    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {
        List<GrantedAuthority> authorities
                = new ArrayList<>();
        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}
