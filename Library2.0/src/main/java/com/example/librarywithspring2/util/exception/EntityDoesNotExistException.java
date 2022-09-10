package com.example.librarywithspring2.util.exception;

public class EntityDoesNotExistException extends IllegalArgumentException{
    public EntityDoesNotExistException(String s) {
        super("This " +  s + " doesn't exists!");
    }
}
