package com.bank.security.banksecurity.controller;

import com.bank.security.banksecurity.config.JwtTokenUtil;
import com.bank.security.banksecurity.domain.User;
import com.bank.security.banksecurity.repository.UserRepository;
import com.bank.security.banksecurity.entity.customer.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    final private AuthenticationManager authenticationManager;

    final private JwtTokenUtil jwtTokenUtil;
    final private UserRepository userRepository;

    AuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserRepository user){
        this.jwtTokenUtil=jwtTokenUtil;
        this.authenticationManager=authenticationManager;
        this.userRepository=user;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResponse<User> register(@RequestBody LoginUser loginUser) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        final User user = userRepository.findByUsername(loginUser.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        return new ApiResponse<>(200, "success",user,new AuthToken(token, user.getUsername()));
    }

    @GetMapping("/no-enable")
    public ApiResponse<List<User>> listRole(){
        return new ApiResponse<>(222, "Account disabled.","noenable");
    }

}
