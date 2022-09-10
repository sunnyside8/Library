package com.example.librarywithspring2.web;


import com.example.librarywithspring2.model.binding.BookBindingModel;
import com.example.librarywithspring2.model.view.BookViewModel;
import com.example.librarywithspring2.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity <List<BookViewModel>> getAllBooks(){
        List<BookViewModel> bookViewModels = bookService.getAllBooks();
        if(bookViewModels.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(bookViewModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookViewModel> getBookById(@PathVariable Long id){
        BookViewModel bookViewModel = bookService.getBookById(id);
        return ResponseEntity.ok().body(bookViewModel);
    }

    @PostMapping
    public ResponseEntity<Void> addNewBook(@RequestBody @Valid BookBindingModel bookRequest,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        BookViewModel bookViewModel = bookService.createBook(bookRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("location", "/created/" + bookViewModel.getId()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteById(id);

        return ResponseEntity.
                noContent().build();
    }



}
