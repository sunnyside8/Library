package com.example.librarywithspring2.service;

import com.example.librarywithspring2.model.entity.RoleEntity;
import com.example.librarywithspring2.model.entity.enu.RolesNameEnum;
import com.example.librarywithspring2.repository.RoleRepository;
import com.example.librarywithspring2.util.exception.DatabaseProblemException;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;


    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleEntity getRoleByRole(RolesNameEnum role) {
        return roleRepository.getRoleByRole(role)
                .orElseThrow(DatabaseProblemException::new);
    }
}
