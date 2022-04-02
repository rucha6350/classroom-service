package com.microservices.classroomutility.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String status;

}