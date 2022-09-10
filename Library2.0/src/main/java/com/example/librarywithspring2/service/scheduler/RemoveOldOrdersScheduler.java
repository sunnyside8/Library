package com.example.librarywithspring2.service.scheduler;

import com.example.librarywithspring2.service.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RemoveOldOrdersScheduler {

    private final OrderService orderService;

    public RemoveOldOrdersScheduler(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(cron = "20 21 4 * * *")
    public void removeOldOrders() {
        this.orderService.getAllOrdersWithParams(null,null,null)
                .forEach(order -> {
                    LocalDate dueDate = order.getDueDate();
                    LocalDate expireDate = dueDate.plusYears(1);
                    if (expireDate.isAfter(LocalDate.now())) {
                        this.orderService.deleteOrderById(order.getId());
                    }
                });
    }
}
