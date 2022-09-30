package com.bank.security.banksecurity.controller;

import com.bank.security.banksecurity.domain.AppUser;
import com.bank.security.banksecurity.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    UserService userService;

    @GetMapping
    public Page<AppUser> getUsers(Pageable pageable) {
        return userService.getUsers(pageable);
    }

    @GetMapping("{id}")
    public AppUser getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    //@IsAdmin
    public AppUser createUser(@Valid @RequestBody AppUser appUser) {
        return userService.createUser(appUser);
    }

    @PutMapping("{id}")
    //@IsAdmin
    public AppUser updateUser(@PathVariable("id") Long id, @Valid @RequestBody AppUser appUser) {
        return userService.updateUser(id, appUser);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

}
