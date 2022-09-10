package com.example.librarywithspring2.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthorBindingModel {

    @NotBlank(message = "Author's name cannot be blank")
    @Size(min = 3,max = 50,message = "Author's name should be between 3 and 50 symbols")
    private String authorName;

    public String getAuthorName() {
        return authorName;
    }

    public AuthorBindingModel setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }
}
