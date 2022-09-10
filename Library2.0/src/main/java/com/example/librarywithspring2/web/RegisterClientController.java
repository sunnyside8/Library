package com.example.librarywithspring2.web;


import com.example.librarywithspring2.model.binding.ClientRegisterBindingModel;
import com.example.librarywithspring2.model.view.ClientViewModel;
import com.example.librarywithspring2.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Controller
public class RegisterClientController {

    private final ClientService clientService;

    public RegisterClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/register")
    public ResponseEntity<ClientViewModel> registerClient(
            @Valid @RequestBody ClientRegisterBindingModel clientRegisterBindingModel, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        ClientViewModel clientViewModel = clientService.register(clientRegisterBindingModel);

        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .header("location", "/created/" + clientViewModel.getId()).build();
    }
}
