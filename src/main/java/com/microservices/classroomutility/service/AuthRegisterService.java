package com.microservices.classroomutility.service;

import com.microservices.classroomutility.vo.GenericResponse;
import com.microservices.classroomutility.entity.User;

public interface AuthRegisterService {

    GenericResponse createUser(User user);

    GenericResponse loginUser(User user);
}