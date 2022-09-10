package com.example.librarywithspring2.repository;

import com.example.librarywithspring2.model.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity,Long> {

    Optional<ClientEntity> findByClientName(String clientName);

    Optional<ClientEntity> findClientByEmail(String email);

    Optional<ClientEntity> findClientByUsername(String username);
}
