package com.example.librarywithspring2.web;

import com.example.librarywithspring2.model.binding.ClientLoginBindingModel;
import com.example.librarywithspring2.model.user.ClientUserDatails;
import com.example.librarywithspring2.model.view.ClientViewModel;
import com.example.librarywithspring2.service.LoginService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.HttpCookie;

@RestController
public class LoginController {
    private final LoginService loginService;


    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<ClientViewModel> login(@AuthenticationPrincipal ClientUserDatails clientUserDatails) {

        ClientViewModel clientViewModel = loginService.authenticateUser(clientUserDatails.getUsername());


        return ResponseEntity.ok().body(clientViewModel);
    }


    @PostMapping("/logout")
    public ResponseEntity<Void> logout(){
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/admin")
    public ResponseEntity<Void> adminPanel(){
        return ResponseEntity.ok().build();
    }

}
