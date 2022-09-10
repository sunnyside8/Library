package com.example.librarywithspring2.model.view;

public class AuthorViewModel {

    private Long id;
    private String authorName;

    public Long getId() {
        return id;
    }

    public AuthorViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public AuthorViewModel setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }
}
