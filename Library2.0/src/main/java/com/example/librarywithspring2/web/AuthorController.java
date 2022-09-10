package com.example.librarywithspring2.web;


import com.example.librarywithspring2.model.binding.AuthorBindingModel;
import com.example.librarywithspring2.model.view.AuthorViewModel;
import com.example.librarywithspring2.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity <List<AuthorViewModel>> getAllAuthors(){
        List<AuthorViewModel> authorViewModels = authorService.getAllAuthors();
        if(authorViewModels.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(authorViewModels);
    }

    @PostMapping
    public ResponseEntity<Void> addNewAuthor(@Valid @RequestBody AuthorBindingModel authorBindingModel,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        AuthorViewModel authorDto = authorService.createNewAuthor(authorBindingModel);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("location", "/created/" + authorDto.getId()).build();
    }

}
