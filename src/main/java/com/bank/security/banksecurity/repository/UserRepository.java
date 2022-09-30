package com.bank.security.banksecurity.repository;

import com.bank.security.banksecurity.entity.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<AppUserEntity, Long> {
    AppUserEntity findByUsername(String username);
}
