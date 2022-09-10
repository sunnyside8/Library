package com.example.librarywithspring2.service;

import com.example.librarywithspring2.model.binding.BookBindingModel;
import com.example.librarywithspring2.model.entity.AuthorEntity;
import com.example.librarywithspring2.model.entity.BookEntity;
import com.example.librarywithspring2.model.view.BookViewModel;
import com.example.librarywithspring2.repository.BookRepository;
import com.example.librarywithspring2.util.exception.EntityAlreadyExistsException;
import com.example.librarywithspring2.util.exception.EntityDoesNotExistException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, AuthorService authorService, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    public BookViewModel createBook(BookBindingModel bookRequest) {
        bookRepository.findByBookTitle(bookRequest.getBookTitle())
                .ifPresent(book -> {
                    throw new EntityAlreadyExistsException("book");
                });

        AuthorEntity authorByName = authorService.findAuthorByName(bookRequest.getAuthorName());

        BookEntity newBook = modelMapper.map(bookRequest, BookEntity.class);

        BookEntity savedBook = bookRepository.save(newBook.setAuthor(authorByName));

        return modelMapper.map(savedBook, BookViewModel.class);
    }

    public BookViewModel getBookById(Long id) {
        return bookRepository.findById(id).map(book-> modelMapper.map(book,BookViewModel.class))
                .orElseThrow( () -> new EntityDoesNotExistException("book"));


    }

    public void deleteById(Long id) {
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> new EntityDoesNotExistException("book"));
        bookRepository.delete(book);
    }

    public List<BookViewModel> getAllBooks() {
        return bookRepository.findAll().stream().map( book -> modelMapper.map(book,BookViewModel.class))
                .collect(Collectors.toList());
    }

    public BookEntity findBookByTitle(String bookTitle) {
        return bookRepository.findByBookTitle(bookTitle)
                .orElseThrow(() -> new EntityDoesNotExistException("book"));
    }
}
