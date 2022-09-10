package com.example.librarywithspring2.service;

import com.example.librarywithspring2.model.binding.ClientRegisterBindingModel;
import com.example.librarywithspring2.model.entity.ClientEntity;
import com.example.librarywithspring2.model.entity.RoleEntity;
import com.example.librarywithspring2.model.entity.enu.RolesNameEnum;
import com.example.librarywithspring2.model.view.ClientViewModel;
import com.example.librarywithspring2.repository.ClientRepository;
import com.example.librarywithspring2.util.exception.EntityAlreadyExistsException;
import com.example.librarywithspring2.util.exception.EntityDoesNotExistException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;


    public ClientService(ClientRepository clientRepository, RoleService roleService, ModelMapper modelMapper, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.clientRepository = clientRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public ClientViewModel getClientById(Long clientId){
        return clientRepository.findById(clientId).map(client -> modelMapper.map(client,ClientViewModel.class))
                .orElseThrow(() -> new EntityDoesNotExistException("client"));
    }

    public ClientEntity getClientEntityById(Long clientId){
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityDoesNotExistException("client"));
    }

    public List<ClientViewModel> getAllClients() {
        return clientRepository.findAll().stream().map(client -> modelMapper.map(client,ClientViewModel.class))
                .collect(Collectors.toList());
    }

    public void deleteClientById(Long id) {
        ClientEntity client = clientRepository.findById(id)
                .orElseThrow(() -> {
                    throw new EntityDoesNotExistException("client");
                });
        clientRepository.delete(client);

    }

    public ClientEntity getClientByName(String clientName) {
        return clientRepository.findByClientName(clientName)
                .orElseThrow(() -> new EntityDoesNotExistException("client"));
    }

    public ClientViewModel register(ClientRegisterBindingModel clientRegisterBindingModel) {
        String clientEmail = clientRegisterBindingModel.getEmail();
        clientRepository.findClientByEmail(clientEmail)
                .ifPresent(client -> {
                    throw new EntityAlreadyExistsException("client with email " + clientEmail);
                });

        String password = clientRegisterBindingModel.getPassword();
        String confirmPassword = clientRegisterBindingModel.getConfirmPassword();

        if(!password.equals(confirmPassword)){
            throw new IllegalArgumentException("Passwords don't match!");
        }
        ClientEntity clientEntity = modelMapper.map(clientRegisterBindingModel,ClientEntity.class);
        clientEntity.setPassword(passwordEncoder.encode(password));

        RoleEntity userRole = roleService.getRoleByRole(RolesNameEnum.USER);
        clientEntity.setRoles(Set.of(userRole));

        emailService.sendRegistrationEmail(clientEmail,clientEntity.getUsername());

        return modelMapper.map(clientRepository.save(clientEntity),ClientViewModel.class);
    }

    public ClientEntity getClientByUsername(String username){
        return clientRepository.findClientByUsername(username)
                .orElseThrow(() -> new EntityDoesNotExistException("user with this username "));
    }
}
