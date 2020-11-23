package by.shymanel.springlab.exceptions.exceptionHandlers;


import by.shymanel.springlab.exceptions.ComputerException;
import by.shymanel.springlab.exceptions.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ComputerExceptionHandler {

    @ExceptionHandler(ComputerException.class)
    public ResponseEntity<Object> handleControllerException(
            ComputerException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
