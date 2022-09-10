package com.example.librarywithspring2.web;

import com.example.librarywithspring2.model.binding.OrderBindingModel;
import com.example.librarywithspring2.model.user.ClientUserDatails;
import com.example.librarywithspring2.model.view.OrderViewModel;
import com.example.librarywithspring2.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private Principal principal;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderViewModel> getOrderById(@PathVariable Long id,
                                                       @AuthenticationPrincipal ClientUserDatails clientUserDatails){
        OrderViewModel orderViewModel = orderService.getOrderById(id);
        if(clientUserDatails.getId() != orderViewModel.getClient().getId()){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok().body(orderViewModel);
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping
//    public ResponseEntity<List<OrderViewModel>> getAllOrders(@RequestParam(required = false) String dateType,
//                                                             @RequestParam(required = false) LocalDate date,
//                                                             @RequestParam(required = false) String option){
//        List<OrderViewModel> getAllOrdersByParams = orderService.getAllOrdersWithParams(dateType,date,option);
//        if (getAllOrdersByParams.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok().body(getAllOrdersByParams);
//    }


    @PostMapping
    public ResponseEntity<Void> addNewOrder(@RequestBody @Valid OrderBindingModel orderBindingModel,
                                            BindingResult bindingResult,
                                            @AuthenticationPrincipal ClientUserDatails clientUserDatails) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        if(clientUserDatails.getId() == null){
            throw new IllegalArgumentException("Please login to proceed!");
        }
        OrderViewModel orderViewModel = orderService.createOrder(orderBindingModel, clientUserDatails.getId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("location", "/created/" + orderViewModel.getId()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);

        return ResponseEntity.
                noContent().build();
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping
//    public Iterable<OrderViewModel> getAllOrders(@RequestParam(required = false) String dateType,
//                                                             @RequestParam(required = false) LocalDate date,
//                                                             @RequestParam(required = false) String option){
//       Iterable<OrderViewModel> getAllOrdersByParams = orderService.getAllOrdersWithParams(dateType,date,option);
//        if (getAllOrdersByParams.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok().body(getAllOrdersByParams);
//    }


}

