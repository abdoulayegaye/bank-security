package com.bank.security.banksecurity.controller;

import com.bank.security.banksecurity.domain.User;
import com.bank.security.banksecurity.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    UserService userService;

    @GetMapping
    public Page<User> getUsers(Pageable pageable) {
        return userService.getUsers(pageable);
    }

    @GetMapping("{/id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    //@IsAdmin
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("{/id}")
    //@IsAdmin
    public User updateUser(@PathVariable("id") Long id, @Valid @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("{/id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

}
