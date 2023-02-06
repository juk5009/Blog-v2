package shop.mtcoding.blogex.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.mtcoding.blogex.handler.ex.CustomException;
import shop.mtcoding.blogex.util.Script;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customException(CustomException e) {

        return new ResponseEntity<>(Script.back(e.getMessage()), e.getStatus());

    }

}
