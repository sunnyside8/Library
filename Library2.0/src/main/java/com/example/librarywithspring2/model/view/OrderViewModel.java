package com.example.librarywithspring2.model.view;

import com.example.librarywithspring2.model.entity.BookEntity;
import com.example.librarywithspring2.model.entity.ClientEntity;

import javax.persistence.*;
import java.time.LocalDate;

public class OrderViewModel {

    private Long id;
    private ClientEntity client;
    private BookEntity book;
    private LocalDate issueDate;
    private LocalDate dueDate;

    public Long getId() {
        return id;
    }

    public OrderViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public ClientEntity getClient() {
        return client;
    }

    public OrderViewModel setClient(ClientEntity client) {
        this.client = client;
        return this;
    }

    public BookEntity getBook() {
        return book;
    }

    public OrderViewModel setBook(BookEntity book) {
        this.book = book;
        return this;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public OrderViewModel setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public OrderViewModel setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }
}
