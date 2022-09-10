package com.example.librarywithspring2.web.exception;

import com.example.librarywithspring2.util.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {
//
//
//    @ExceptionHandler(NumberFormatException.class)
//    public ResponseEntity<String> handleInvalidNumberFormat(NumberFormatException exception) {
//        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<String> handleEntityAlreadyExists(EntityAlreadyExistsException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
//
//    @ExceptionHandler(EntityCouldNotBeDeletedException.class)
//    public ResponseEntity<String> handleEntityNotUpdated(EntityCouldNotBeDeletedException exception) {
//        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
//
//    }
//
//    @ExceptionHandler(EntityCouldNotBeUpdatedException.class)
//    public ResponseEntity<String> handleEntityNotDeleted(EntityCouldNotBeUpdatedException exception) {
//        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
//        //should have been 304
//    }
//
//    @ExceptionHandler(EntityDoesNotExistException.class)
//    public ResponseEntity<String> handleEntityDoesNotExists(EntityDoesNotExistException exception) {
//        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
//        //should have been 404
//    }
//
//    @ExceptionHandler(EntityCouldNotBeCreatedException.class)
//    public ResponseEntity<String> handleEntityCouldNotBeCreated(EntityCouldNotBeCreatedException exception) {
//        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
//
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DatabaseProblemException.class)
    public ResponseEntity<String> handleDatabaseProblem(DatabaseProblemException exception) {
        return new ResponseEntity<>("Database problem at hand.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
//
//
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleInvalidBody(HttpMessageNotReadableException exception) {
        return new ResponseEntity<>("The content that you are sending is not in the correct format/missing.", HttpStatus.BAD_REQUEST);
    }


//
//    @ExceptionHandler(SQLException.class)
//    public ResponseEntity<String> handleSQLException(SQLException exception) {
//        return new ResponseEntity<>("Something went wrong with the database.Please check it and try again",
//                HttpStatus.SERVICE_UNAVAILABLE);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleUnexpectedException(Exception exception) {
//        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
//    }

}

