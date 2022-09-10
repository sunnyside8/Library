package com.example.librarywithspring2.model.entity;


import com.example.librarywithspring2.model.entity.enu.RolesNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RolesNameEnum role;

    public RoleEntity() {
    }

    public RolesNameEnum getRole() {
        return role;
    }

    public void setRole(RolesNameEnum role) {
        this.role = role;
    }
}
