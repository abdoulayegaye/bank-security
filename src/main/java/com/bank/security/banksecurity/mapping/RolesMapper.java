package com.bank.security.banksecurity.mapping;

import com.bank.security.banksecurity.domain.Role;
import com.bank.security.banksecurity.entity.AppRolesEntity;
import org.mapstruct.Mapper;

@Mapper
public interface RolesMapper {
    Role toRoles(AppRolesEntity appRolesEntity);
    AppRolesEntity fromRoles(Role role);
}
