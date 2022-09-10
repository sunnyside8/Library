package com.example.librarywithspring2.service;

import com.example.librarywithspring2.model.binding.AuthorBindingModel;
import com.example.librarywithspring2.model.entity.AuthorEntity;
import com.example.librarywithspring2.model.view.AuthorViewModel;
import com.example.librarywithspring2.repository.AuthorRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    AuthorBindingModel authorBindingModel;
    AuthorEntity authorEntity;
    AuthorViewModel authorViewModel;

    @Mock
    AuthorRepository authorRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    AuthorService authorService;

    @BeforeEach
    public void setUp() {
    authorBindingModel = new AuthorBindingModel().setAuthorName("Ime");
    authorEntity = new AuthorEntity().setAuthorName("Ime");
    authorViewModel = new AuthorViewModel().setAuthorName("Ime");
    }

    @Test
    void createNewAuthor_alreadyExisting_throw() {
        when(authorRepository.findByAuthorName("Ime")).thenReturn(Optional.of(authorEntity));
        assertThrows(EntityAlreadyExistsException.class,
                () -> authorService.createNewAuthor(authorBindingModel));
    }

    @Test
    void createNewAuthor_okay() {
        when(authorRepository.findByAuthorName("Ime")).thenReturn(Optional.empty());
        when( authorRepository.save(authorEntity)).thenReturn(authorEntity);
        when(modelMapper.map(authorEntity,AuthorViewModel.class)).thenReturn(authorViewModel);
        assertEquals(authorService.createNewAuthor(authorBindingModel),authorViewModel);
    }

    @Test
    void findAuthorByName_throw() {
        when(authorRepository.findByAuthorName("Ime")).thenReturn(Optional.empty());
        assertThrows(EntityDoesNotExistException.class,
                () -> authorService.findAuthorByName("Ime"));
    }

    @Test
    void findAuthorByName_okay() {
        when(authorRepository.findByAuthorName("Ime")).thenReturn(Optional.of(authorEntity));
        assertEquals(authorService.findAuthorByName("Ime"),authorEntity);
    }

    @Test
    void getAllAuthors() {
        List<AuthorViewModel> authors = List.of(authorViewModel);
        when(authorRepository.findAll()).thenReturn(List.of(authorEntity));
        when(modelMapper.map(authorEntity,AuthorViewModel.class)).thenReturn(authorViewModel);
        assertEquals(authorService.getAllAuthors(),authors);


    }
}