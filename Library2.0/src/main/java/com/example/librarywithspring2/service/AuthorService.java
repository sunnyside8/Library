package com.example.librarywithspring2.service;

import com.example.librarywithspring2.model.binding.AuthorBindingModel;
import com.example.librarywithspring2.model.entity.AuthorEntity;
import com.example.librarywithspring2.model.view.AuthorViewModel;
import com.example.librarywithspring2.repository.AuthorRepository;
import com.example.librarywithspring2.util.exception.EntityAlreadyExistsException;
import com.example.librarywithspring2.util.exception.EntityDoesNotExistException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public AuthorService(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    public AuthorViewModel createNewAuthor(AuthorBindingModel authorBindingModel) {
        String authorName = authorBindingModel.getAuthorName();
        authorRepository.findByAuthorName(authorName)
                .ifPresent(authorTry -> {
                    throw new EntityAlreadyExistsException("author with name " + authorTry.getAuthorName());
                });
        AuthorEntity authorEntity =
                new AuthorEntity().setAuthorName(authorName);

        AuthorEntity savedAuthor = authorRepository.save(authorEntity);

        return modelMapper.map(savedAuthor,AuthorViewModel.class);
    }

    public AuthorEntity findAuthorByName(String authorName){
        return authorRepository.findByAuthorName(authorName)
                .orElseThrow(()-> new EntityDoesNotExistException("author"));
    }

    public List<AuthorViewModel> getAllAuthors() {
        return authorRepository.findAll().stream().map(a -> modelMapper.map(a,AuthorViewModel.class))
                .collect(Collectors.toList());
    }
}
