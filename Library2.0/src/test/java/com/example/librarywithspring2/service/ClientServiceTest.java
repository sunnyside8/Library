package com.example.librarywithspring2.service;

import com.example.librarywithspring2.model.binding.ClientRegisterBindingModel;
import com.example.librarywithspring2.model.entity.ClientEntity;
import com.example.librarywithspring2.model.view.ClientViewModel;
import com.example.librarywithspring2.repository.ClientRepository;
import com.example.librarywithspring2.util.exception.EntityAlreadyExistsException;
import com.example.librarywithspring2.util.exception.EntityDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    ClientEntity clientEntity;
    ClientViewModel clientViewModel;
    ClientRegisterBindingModel clientBindingModel;

    @Mock
    ClientRepository clientRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    ClientService clientService;

    @BeforeEach
    public void setUp() {
       clientEntity  = new ClientEntity().setClientName("Ime");
       clientViewModel = new ClientViewModel().setClientName("Ime");
       clientBindingModel = new ClientRegisterBindingModel().setClientName("Ime");
    }


    @Test
    void getClientById_throw() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityDoesNotExistException.class,
                () -> clientService.getClientById(1L));

    }

    @Test
    void getClientById_okay() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(clientEntity));
        when(modelMapper.map(clientEntity,ClientViewModel.class)).thenReturn(clientViewModel);
        assertEquals(clientService.getClientById(1L),clientViewModel);

    }

    @Test
    void getAllClients() {
        List<ClientEntity> clients = List.of(clientEntity);
        when(clientRepository.findAll()).thenReturn(clients);
        when(modelMapper.map(clientEntity,ClientViewModel.class)).thenReturn(clientViewModel);

        assertEquals(clientService.getAllClients(),List.of(clientViewModel));
    }

    @Test
    void deleteClientById() {
    }

    @Test
    void getClientByName_throw() {
        when(clientRepository.findByClientName("Ime")).thenReturn(Optional.empty());
        assertThrows(EntityDoesNotExistException.class,
                () -> clientService.getClientByName("Ime"));
    }

    @Test
    void getClientByName_okay() {
        when(clientRepository.findByClientName("Ime")).thenReturn(Optional.of(clientEntity));
        assertEquals(clientService.getClientByName("Ime"),clientEntity);
    }
}