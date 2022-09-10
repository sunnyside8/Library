package com.example.librarywithspring2.repository;

import com.example.librarywithspring2.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

    List<OrderEntity> findAllByClient_Id(Long client_id);

    List<OrderEntity> findAllByIssueDateBefore(LocalDate issueDate);

    List<OrderEntity> findAllByIssueDateAfter(LocalDate issueDate);

    List <OrderEntity> findAllByIssueDate(LocalDate issueDate);

    List<OrderEntity> findAllByDueDateAfter(LocalDate issueDate);

    List<OrderEntity> findAllByDueDateBefore(LocalDate issueDate);

    List<OrderEntity> findAllByDueDate(LocalDate dueDate);
}
