package com.example.librarywithspring2.model.view;

import com.example.librarywithspring2.model.entity.AuthorEntity;

import java.time.LocalDate;

public class BookViewModel {

    private Long id;

    private String bookTitle;

    private LocalDate dateOfPublishing;

    private AuthorEntity author;

    public Long getId() {
        return id;
    }

    public BookViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public BookViewModel setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
        return this;
    }

    public LocalDate getDateOfPublishing() {
        return dateOfPublishing;
    }

    public BookViewModel setDateOfPublishing(LocalDate dateOfPublishing) {
        this.dateOfPublishing = dateOfPublishing;
        return this;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public BookViewModel setAuthor(AuthorEntity author) {
        this.author = author;
        return this;
    }
}
