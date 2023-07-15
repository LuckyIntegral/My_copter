package my.copter.controller.adviser;

import my.copter.exception.EntityNotFoundException;
import my.copter.exception.UserNotFoundException;
import my.copter.exception.BadRequestException;
import my.copter.exception.EmptyFieldException;
import my.copter.data.response.DataContainer;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<DataContainer<String>> handleEntityNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DataContainer<>(exception.getMessage()));
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<DataContainer<String>> handleBadRequestException(BadRequestException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DataContainer<>(exception.getMessage()));
    }

    @ExceptionHandler(value = EmptyFieldException.class)
    public ResponseEntity<DataContainer<String>> handleEmptyFieldException(EmptyFieldException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DataContainer<>(exception.getMessage()));
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<DataContainer<String>> handleUserNotFoundException(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DataContainer<>(exception.getMessage()));
    }
}
