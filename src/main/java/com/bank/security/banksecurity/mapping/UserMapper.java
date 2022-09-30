package com.bank.security.banksecurity.mapping;

import com.bank.security.banksecurity.domain.AppUser;
import com.bank.security.banksecurity.entity.AppUserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    AppUser toUser(AppUserEntity appUserEntity);
    AppUserEntity fromUser(AppUser appUser);
}
