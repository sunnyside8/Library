package com.example.librarywithspring2.model.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @ManyToOne
    private ClientEntity client;

    @OneToOne
    private BookEntity book;


    public OrderEntity() {
    }

    public Long getId() {
        return id;
    }

    public OrderEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public ClientEntity getClient() {
        return client;
    }

    public OrderEntity setClient(ClientEntity client) {
        this.client = client;
        return this;
    }

    public BookEntity getBook() {
        return book;
    }

    public OrderEntity setBook(BookEntity book) {
        this.book = book;
        return this;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public OrderEntity setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public OrderEntity setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    @Override
    public String toString() {
        return "Order:" +
                "client -- " + client.getClientName() +
                ", book -- " + book.getBookTitle() +
                ", issueDate -- " + issueDate +
                ", dueDate -- " + dueDate;
    }
}
