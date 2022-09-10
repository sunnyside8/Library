package com.example.librarywithspring2.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_title",nullable = false)
    private String bookTitle;

    @Column(name = "date_of_publishing")
    private LocalDate dateOfPublishing;

    @ManyToOne(fetch = FetchType.EAGER)
    private AuthorEntity author;


    public BookEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public BookEntity setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
        return this;
    }

    public LocalDate getDateOfPublishing() {
        return dateOfPublishing;
    }

    public BookEntity setDateOfPublishing(LocalDate dateOfPublishing) {
        this.dateOfPublishing = dateOfPublishing;
        return this;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public BookEntity setAuthor(AuthorEntity author) {
        this.author = author;
        return this;
    }
}
