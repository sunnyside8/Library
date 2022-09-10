package com.example.librarywithspring2.model.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ClientLoginBindingModel {
    @NotBlank(message = "Username's name cannot be blank")
    @Size(min = 3,max = 25, message = "Name's length should be between 3 and 25 symbols")
    private String username;

    @NotBlank(message = "Password's name cannot be blank")
    @Size(min = 5,max = 30, message = "Password's length should be between 5 and 30 symbols")
    private String password;

    public String getUsername() {
        return username;
    }

    public ClientLoginBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ClientLoginBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
