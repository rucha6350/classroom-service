package com.microservices.classroomutility.controllers;

import com.microservices.classroomutility.entity.User;
import com.microservices.classroomutility.service.AuthRegisterService;
import com.microservices.classroomutility.vo.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AuthAndRegisterController {

    @Autowired
    private AuthRegisterService service;

    @PostMapping("/createUser")
    public GenericResponse createUser(@RequestBody User user){
        return service.createUser(user);
    }

    @PostMapping("/loginUser")
    public GenericResponse loginUser(@RequestBody User user){
        return service.loginUser(user);
    }

}