package com.example.librarywithspring2.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;


@Configuration
public class ApplicationBeanConfiguration {


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.addConverter(new Converter<Date, LocalDateTime>() {
//            @Override
//            public LocalDateTime convert(MappingContext<Date, LocalDateTime> mappingContext) {
//                return LocalDateTime.parse(mappingContext.getSource(),
//                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            }
//        });
        return modelMapper;
    }





}
