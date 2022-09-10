package com.example.librarywithspring2.repository;

import com.example.librarywithspring2.model.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long> {

    Optional<BookEntity> findByBookTitle(String bookTitle);
}
