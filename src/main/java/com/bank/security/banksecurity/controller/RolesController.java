package com.bank.security.banksecurity.controller;

import com.bank.security.banksecurity.domain.Role;
import com.bank.security.banksecurity.service.RolesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RolesController {

    RolesService rolesService;

    @GetMapping
    public List<Role> getRoles() {
        return rolesService.getRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRole(@PathVariable("id") Long id) {
        return ResponseEntity.ok(rolesService.getRole(id));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role role) {
        return new ResponseEntity<>(rolesService.createRole(role), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable("id") Long id, @Valid @RequestBody Role role) {
        return rolesService.updateRole(id, role);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable("id") Long id) {
        rolesService.deleteRole(id);
    }

}
