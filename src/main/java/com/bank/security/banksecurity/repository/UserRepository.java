package com.bank.security.banksecurity.repository;

import com.bank.security.banksecurity.entity.AppUserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<AppUserEntity, Long> {
}
