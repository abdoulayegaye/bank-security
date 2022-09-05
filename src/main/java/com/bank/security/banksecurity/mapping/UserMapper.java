package com.bank.security.banksecurity.mapping;

import com.bank.security.banksecurity.domain.User;
import com.bank.security.banksecurity.entity.AppUserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User toUser(AppUserEntity appUserEntity);
    AppUserEntity fromUser(User user);
}
