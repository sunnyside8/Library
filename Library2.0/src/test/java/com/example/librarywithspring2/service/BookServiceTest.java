package com.example.librarywithspring2.service;

import com.example.librarywithspring2.model.binding.AuthorBindingModel;
import com.example.librarywithspring2.model.binding.BookBindingModel;
import com.example.librarywithspring2.model.entity.AuthorEntity;
import com.example.librarywithspring2.model.entity.BookEntity;
import com.example.librarywithspring2.model.view.AuthorViewModel;
import com.example.librarywithspring2.model.view.BookViewModel;
import com.example.librarywithspring2.repository.BookRepository;
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
class BookServiceTest {

    BookViewModel bookViewModel;
    BookEntity bookEntity;
    BookBindingModel bookBindingModel;
    AuthorEntity authorEntity;

    @Mock
    BookRepository bookRepository;

    @Mock
    AuthorService authorService;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    BookService bookService;

    @BeforeEach
    public void setUp() {
        bookViewModel = new BookViewModel().setBookTitle("Zaglavie");
        bookEntity = new BookEntity().setBookTitle("Zaglavie");
        bookBindingModel = new BookBindingModel().setBookTitle("Zaglavie");
        authorEntity = new AuthorEntity();
    }

    @Test
    void createBook_alreadyExists_throw() {
        when(bookRepository.findByBookTitle("Zaglavie")).thenReturn(Optional.of(bookEntity));
        assertThrows(EntityAlreadyExistsException.class,
                () -> bookService.createBook(bookBindingModel));
    }


    @Test
    void createBook_okay() {
        when(bookRepository.findByBookTitle("Zaglavie")).thenReturn(Optional.empty());
        when(authorService.findAuthorByName("Ime")).thenReturn(authorEntity);
        when(modelMapper.map(bookBindingModel,BookEntity.class)).thenReturn(bookEntity);
        when(bookRepository.save(bookEntity)).thenReturn(bookEntity);
        when(modelMapper.map(bookEntity,BookViewModel.class)).thenReturn(bookViewModel);

        assertEquals(bookService.createBook(bookBindingModel.setAuthorName("Ime")),bookViewModel.setAuthor(authorEntity));
    }

    @Test
    void getBookById_throw() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityDoesNotExistException.class,
                () -> bookService.getBookById(1L));
    }

    @Test
    void getBookById_okay() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(bookEntity));
        when(modelMapper.map(bookEntity,BookViewModel.class)).thenReturn(bookViewModel);

        assertEquals(bookService.getBookById(1L),bookViewModel);
    }

    @Test
    void deleteById() {


    }

    @Test
    void getAllBooks() {
        List<BookEntity> bookEntities = List.of(bookEntity);
        when(bookRepository.findAll()).thenReturn(bookEntities);
        when(modelMapper.map(bookEntity,BookViewModel.class)).thenReturn(bookViewModel);

        assertEquals(bookService.getAllBooks(),List.of(bookViewModel));

    }

    @Test
    void findBookByTitle_okay() {
        when(bookRepository.findByBookTitle("Ime")).thenReturn(Optional.of(bookEntity));

        assertEquals(bookService.findBookByTitle("Ime"),bookEntity);
    }

    @Test
    void findBookByTitle_throw() {
        when(bookRepository.findByBookTitle("Ime")).thenReturn(Optional.empty());
        assertThrows(EntityDoesNotExistException.class,
                () -> bookService.findBookByTitle("Ime"));
    }
}