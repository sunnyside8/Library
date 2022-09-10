package com.example.librarywithspring2.service;

import com.example.librarywithspring2.model.binding.OrderBindingModel;
import com.example.librarywithspring2.model.entity.BookEntity;
import com.example.librarywithspring2.model.entity.ClientEntity;
import com.example.librarywithspring2.model.entity.OrderEntity;
import com.example.librarywithspring2.model.view.OrderViewModel;
import com.example.librarywithspring2.repository.OrderRepository;
import com.example.librarywithspring2.util.exception.EntityDoesNotExistException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final BookService bookService;
    private final ModelMapper modelMapper;

    public OrderService(OrderRepository orderRepository, ClientService clientService, BookService bookService,
                        ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }


    public OrderViewModel createOrder(OrderBindingModel orderBindingModel, Long clientId) {
        BookEntity bookByTitle =
                bookService.findBookByTitle(orderBindingModel.getBookTitle());

        ClientEntity clientEntity =
                clientService.getClientEntityById(clientId);

        OrderEntity orderEntity = new OrderEntity()
                .setBook(bookByTitle)
                .setClient(clientEntity)
                .setIssueDate(LocalDate.now())
                .setDueDate(LocalDate.now().plusMonths(1));
        orderRepository.save(orderEntity);

        return modelMapper.map(orderEntity, OrderViewModel.class);

    }

    public OrderViewModel getOrderById(Long id) {
        return orderRepository.findById(id).map(order -> modelMapper.map(order, OrderViewModel.class))
                .orElseThrow(() -> new EntityDoesNotExistException("order"));
    }

    public void deleteOrderById(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("order"));
        orderRepository.delete(orderEntity);
    }

    public List<OrderViewModel> getAllOrdersWithParams(String dateType, LocalDate date, String option) {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        if (dateType != null && !dateType.isBlank() ) {
            if(date == null){
                throw new IllegalArgumentException("Date cannot be null if you enter search parameters.");
            }
            if (dateType.equals("issueDate")) {
                orderEntities = switch (option) {
                    case "before" -> orderEntities.stream().filter(order -> order.getIssueDate().isBefore(date))
                            .collect(Collectors.toList());
                    case "after" -> orderEntities.stream().filter(order -> order.getIssueDate().isAfter(date))
                            .collect(Collectors.toList());
                    case "on" -> orderEntities.stream().filter(order -> order.getIssueDate().equals(date))
                            .collect(Collectors.toList());
                    default -> throw new IllegalStateException(
                            ("Invalid option type option.Choose between : before, after or on ."));
                };

            } else if (dateType.equals("dueDate")) {
                orderEntities = switch (option) {
                    case "before" -> orderEntities.stream().filter(order -> order.getDueDate().isBefore(date))
                            .collect(Collectors.toList());
                    case "after" -> orderEntities.stream().filter(order -> order.getDueDate().isAfter(date))
                            .collect(Collectors.toList());
                    case "on" -> orderEntities.stream().filter(order -> order.getDueDate().equals(date))
                            .collect(Collectors.toList());
                    default -> throw new IllegalArgumentException(
                            ("Invalid option type option.Choose between : before, after or on ."));
                };

            } else {
                throw new IllegalArgumentException("Invalid dateType option.Choose between : issueDate and dueDate.");
            }
        }

        return orderEntities.stream().map(o -> modelMapper.map(o, OrderViewModel.class)).collect(Collectors.toList());
    }

    public List<OrderViewModel> getOrdersByClientId(Long id) {
        List<OrderEntity> allByClient_id = orderRepository.findAllByClient_Id(id);
        if(allByClient_id.isEmpty()){
            return new ArrayList<>();
        }
        return allByClient_id.stream().map(order -> modelMapper.map(order,OrderViewModel.class))
                .collect(Collectors.toList());
    }

    public void removeOrderById(Long id){
        orderRepository.deleteById(id);
    }
}
