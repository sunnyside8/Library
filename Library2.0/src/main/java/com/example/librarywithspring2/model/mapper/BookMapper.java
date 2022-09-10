package com.example.librarywithspring2.model.mapper;

import com.example.librarywithspring2.model.binding.BookBindingModel;
import com.example.librarywithspring2.model.entity.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookEntity bookDtoToBookEntity(BookBindingModel bookBindingModel);
}
