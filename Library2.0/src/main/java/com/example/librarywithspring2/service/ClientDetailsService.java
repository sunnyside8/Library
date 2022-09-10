package com.example.librarywithspring2.service;

import com.example.librarywithspring2.model.entity.ClientEntity;
import com.example.librarywithspring2.model.entity.RoleEntity;
import com.example.librarywithspring2.model.user.ClientUserDatails;
import com.example.librarywithspring2.repository.ClientRepository;
import com.example.librarywithspring2.util.exception.EntityDoesNotExistException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class ClientDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;

    public ClientDetailsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientUserDatails loadUserByUsername(String username) {
        return clientRepository.
                findClientByUsername(username)
                .map(this::map)
                .orElseThrow(() -> new EntityDoesNotExistException("user with this username"));
    }

    private ClientUserDatails map(ClientEntity clientEntity) {

        return new ClientUserDatails(
                clientEntity.getId(),
                clientEntity.getPassword(),
                clientEntity.getUsername(),
                clientEntity.getClientName(),
                clientEntity.getEmail(),
                clientEntity.
                        getRoles().
                        stream().
                        map(this::map).
                        toList());
    }

    private GrantedAuthority map(RoleEntity clientRole) {
        return new SimpleGrantedAuthority("ROLE_" +
                clientRole.
                        getRole().name());
    }
}
