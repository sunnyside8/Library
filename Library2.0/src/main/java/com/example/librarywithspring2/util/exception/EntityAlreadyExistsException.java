package com.example.librarywithspring2.util.exception;

public class EntityAlreadyExistsException extends IllegalArgumentException{

    public EntityAlreadyExistsException(String message) {
        super("This " +  message + " already exists!");
    }
}
