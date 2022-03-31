package com.microservices.classroomutility.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorMessage {
    private String errorCode;
    private String errorMessage;
}
