package com.example.librarywithspring2.repository;

import com.example.librarywithspring2.model.entity.RoleEntity;
import com.example.librarywithspring2.model.entity.enu.RolesNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {

    Optional<RoleEntity> getRoleByRole(RolesNameEnum role);
}
