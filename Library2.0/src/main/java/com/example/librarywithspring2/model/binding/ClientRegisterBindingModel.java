package com.example.librarywithspring2.model.binding;

import com.example.librarywithspring2.model.validation.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ClientRegisterBindingModel {

    @NotBlank(message = "Client's name cannot be blank")
    @Size(min = 2,max = 50, message = "Name's length should be between 2 and 50 symbols")
    private String clientName;

    @NotBlank(message = "Username's name cannot be blank")
    @Size(min = 3,max = 25, message = "Name's length should be between 3 and 25 symbols")
    @UniqueUsername(message = "Username should be unique")
    private String username;

    @NotBlank(message = "Email's name cannot be blank")
    @Email(message = "Email must be in valid format")
    private String email;

    @NotBlank(message = "Password's name cannot be blank")
    @Size(min = 5,max = 30, message = "Password's length should be between 5 and 30 symbols")
    private String password;

    @NotBlank(message = "Password's name cannot be blank")
    @Size(min = 5,max = 30, message = "Password's length should be between 5 and 30 symbols")
    private String confirmPassword;

    public String getClientName() {
        return clientName;
    }

    public ClientRegisterBindingModel setClientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ClientRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ClientRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ClientRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public ClientRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
