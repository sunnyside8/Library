package com.example.librarywithspring2.model.binding;

import com.example.librarywithspring2.model.entity.AuthorEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class BookBindingModel {

   @NotBlank(message = "Book's name cannot be blank.")
   @Size(min = 3, max = 40,message = "Book's name should be between 3 and 40 symbols inclusive.")
    private String bookTitle;

    @PastOrPresent(message = "Date of publishing cannot be a future date")
    private LocalDate dateOfPublishing;

    @NotBlank(message = "Author's name cannot be blank")
    @Size(min = 3,max = 50,message = "Author's name should be between 3 and 50 symbols")
    private String authorName;

    public String getBookTitle() {
        return bookTitle;
    }

    public BookBindingModel setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
        return this;
    }

    public LocalDate getDateOfPublishing() {
        return dateOfPublishing;
    }

    public BookBindingModel setDateOfPublishing(LocalDate dateOfPublishing) {
        this.dateOfPublishing = dateOfPublishing;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public BookBindingModel setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }
}
