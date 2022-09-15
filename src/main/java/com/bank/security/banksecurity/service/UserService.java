package com.bank.security.banksecurity.service;

import com.bank.security.banksecurity.domain.User;
import com.bank.security.banksecurity.exception.EntityNotFoundException;
import com.bank.security.banksecurity.exception.RequestException;
import com.bank.security.banksecurity.mapping.UserMapper;
import com.bank.security.banksecurity.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    MessageSource messageSource;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toUser);
    }

    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userMapper.toUser(userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id},
                        Locale.getDefault()))));
    }

    @Transactional
    public User createUser(User user) {
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userMapper.toUser(userRepository.save(userMapper.fromUser(user)));
    }

    @Transactional
    public User updateUser(Long id, User user){
        user.setId(id);
        return userMapper.toUser(userRepository.save(userMapper.fromUser(user)));
    }

    @Transactional
    public void deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("user.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }


}
