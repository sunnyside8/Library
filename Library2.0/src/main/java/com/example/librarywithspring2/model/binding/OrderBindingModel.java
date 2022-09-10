package com.example.librarywithspring2.model.binding;

import com.example.librarywithspring2.model.entity.BookEntity;
import com.example.librarywithspring2.model.entity.ClientEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class OrderBindingModel {

//    @NotBlank(message = "Client Name cannot be blank")
//    @Size(min = 2,max = 50, message = "Client name's length should be between 1 and 50 symbols")
//    private String clientName;

    @NotBlank(message = "Book title cannot be blank")
    @Size(min = 3, max = 40,message = "Book's name should be between 3 and 40 symbols inclusive.")
    private String bookTitle;


    public String getBookTitle() {
        return bookTitle;
    }

    public OrderBindingModel setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
        return this;
    }
}
