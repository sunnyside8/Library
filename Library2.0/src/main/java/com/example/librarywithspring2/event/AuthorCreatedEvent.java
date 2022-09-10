package com.example.librarywithspring2.event;

import org.springframework.context.ApplicationEvent;

public class AuthorCreatedEvent extends ApplicationEvent {


    public AuthorCreatedEvent(Object source
                              ) {
        super(source);
    }
}
