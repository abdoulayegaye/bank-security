package com.bank.security.banksecurity.service;

import com.bank.security.banksecurity.domain.Role;
import com.bank.security.banksecurity.exception.EntityNotFoundException;
import com.bank.security.banksecurity.exception.RequestException;
import com.bank.security.banksecurity.mapping.RolesMapper;
import com.bank.security.banksecurity.repository.RolesRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class RolesService {

    RolesRepository rolesRepository;
    RolesMapper rolesMapper;
    MessageSource messageSource;

    @Transactional(readOnly = true)
    public List<Role> getRoles() {
        return StreamSupport.stream(rolesRepository.findAll().spliterator(), false)
                .map(rolesMapper::toRoles)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Role getRole(Long id) {
        return rolesMapper.toRoles(rolesRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage("roles.notfound", new Object[]{id},
                        Locale.getDefault()))));
    }

    @Transactional
    public Role createRole(Role role) {
        rolesRepository.findById(role.getId())
                .ifPresent(entity -> {
                    throw new RequestException(messageSource.getMessage("roles.exists", new Object[]{role.getId()},
                            Locale.getDefault()), HttpStatus.CONFLICT);
                });
        return rolesMapper.toRoles(rolesRepository.save(rolesMapper.fromRoles(role)));
    }

    @Transactional
    public Role updateRole(Long id, Role role) {
        return rolesRepository.findById(id)
                .map(entity -> {
                    role.setId(id);
                    return rolesMapper.toRoles(
                            rolesRepository.save(rolesMapper.fromRoles(role)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("roles.notfound", new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteRole(Long id) {
        try {
            rolesRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("roles.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }


}
