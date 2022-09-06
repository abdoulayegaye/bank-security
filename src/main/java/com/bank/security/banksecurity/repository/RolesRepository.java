package com.bank.security.banksecurity.repository;

import com.bank.security.banksecurity.entity.AppRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<AppRolesEntity, Long> {
}
