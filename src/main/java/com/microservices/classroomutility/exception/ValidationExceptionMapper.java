package com.microservices.classroomutility.exception;

import com.microservices.classroomutility.vo.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ValidationExceptionMapper {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> handleException(Exception ex){
        ErrorMessage errorMessage=new ErrorMessage();
        errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.toString());

        if (ex instanceof ValidationException) {
            errorMessage.setErrorMessage(ex.getMessage());
        }
        else if(ex instanceof NullPointerException){
            errorMessage.setErrorMessage("NULL_POINTER_EXCEPTION");
        }
        else{
            errorMessage.setErrorMessage("INTERNAL_ERROR");
        }
        return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.BAD_REQUEST);
    }
}
