package com.microservices.classroomutility.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationException extends RuntimeException {

    private String errorMessage;

    public ValidationException(){
        super();
    }

    public ValidationException(String errorCode){
        super(errorCode);
    }

    public ValidationException(String errorCode, String errorMessage){
        super(errorCode);
        this.errorMessage=errorMessage;
    }
}
