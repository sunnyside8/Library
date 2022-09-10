package com.example.librarywithspring2.web;


import com.example.librarywithspring2.model.binding.ClientRegisterBindingModel;
import com.example.librarywithspring2.model.user.ClientUserDatails;
import com.example.librarywithspring2.model.view.ClientViewModel;
import com.example.librarywithspring2.model.view.OrderViewModel;
import com.example.librarywithspring2.service.ClientService;
import com.example.librarywithspring2.service.OrderService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final OrderService orderService;

    public ClientController(ClientService clientService, OrderService orderService) {
        this.clientService = clientService;
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ClientViewModel>>> getAllClients(
            @AuthenticationPrincipal ClientUserDatails clientUserDatails) {
        List<EntityModel<ClientViewModel>> allClients = clientService.getAllClients().stream()
                .map(c -> EntityModel.of(c, getClientLinks(c)))
                .collect(Collectors.toList());

        if (allClients.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(CollectionModel.of(allClients));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ClientViewModel>> getClientById(
            @PathVariable Long id,
            @AuthenticationPrincipal ClientUserDatails clientUserDatails) {

        ClientViewModel client = clientService.getClientById(id);

        if(!Objects.equals(clientUserDatails.getId(), client.getId())){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(EntityModel.of(client, getClientLinks(client)));
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<OrderViewModel>> getClientOrders(@PathVariable("id") Long id) {

        List<OrderViewModel> orders = orderService.getOrdersByClientId(id);

        return ResponseEntity.
                ok().body(orders);
    }

//    @PostMapping
//    public ResponseEntity<Void> addNewClient(@RequestBody @Valid ClientRegisterBindingModel clientBindingModel,
//                                             BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            throw new IllegalArgumentException(bindingResult.getFieldErrors().get(0).getDefaultMessage());
//        }
////        ClientViewModel clientViewModel = clientService.createNewClient(clientBindingModel);
//
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .header("location", "/created/" + 1).build();
//    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);

        return ResponseEntity.
                noContent().build();
    }

    private Link[] getClientLinks(ClientViewModel clientViewModel) {
        List<Link> clientLinks = new ArrayList<>();

//        Link selfRel = linkTo(methodOn(ClientController.class).getClientById(clientViewModel.getId())).withSelfRel();
//        clientLinks.add(selfRel);

        Link deleteLink = linkTo(methodOn(ClientController.class).deleteClient(clientViewModel.getId()))
                .withRel("delete");
        clientLinks.add(deleteLink);

        Link orderLink = linkTo(methodOn(ClientController.class).
                getClientOrders(clientViewModel.getId())).withRel("orders");
        clientLinks.add(orderLink);

        return clientLinks.toArray(new Link[0]);
    }


}
