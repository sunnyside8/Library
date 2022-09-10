package com.example.librarywithspring2.service;

import com.example.librarywithspring2.model.user.ClientUserDatails;
import com.example.librarywithspring2.model.view.ClientViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LoginService {
    private final ClientDetailsService clientDetailsService;
    private final ClientService clientService;
    private final ModelMapper modelMapper;



    public LoginService(ClientDetailsService clientDetailsService, ClientService clientService, ModelMapper modelMapper) {
        this.clientDetailsService = clientDetailsService;
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }


    public ClientViewModel authenticateUser(
           String username) {

        ClientUserDatails clientUserDatails = clientDetailsService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken
                (clientUserDatails, clientUserDatails.getPassword(),
                        clientUserDatails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return modelMapper.map(clientDetailsService.loadUserByUsername(username), ClientViewModel.class);


    }
}
