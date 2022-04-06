package com.microservices.classroomutility.controllers;

import com.microservices.classroomutility.entity.DBUserInterface;
import com.microservices.classroomutility.entity.User;
import com.microservices.classroomutility.service.AuthRegisterService;
import com.microservices.classroomutility.vo.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

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

    @PostMapping("/forgetPassword")
    public GenericResponse forgetPassword(@RequestBody User user) throws UnsupportedEncodingException, MessagingException {
        return service.forgetPassword(user);
    }

    @GetMapping("/otpVerify")
    public GenericResponse otpVerification(@RequestParam String otp){
        return service.otpVerification(otp);
    }

    @PostMapping("/updatePassword")
    public GenericResponse updatePassword(@RequestBody User user,@RequestParam String updatedPassword){
        return service.updatePassword(user,updatedPassword);
    }

//    @GetMapping("/sendEmail")
//    public void sendEmail(@RequestBody User user) throws UnsupportedEncodingException, MessagingException {
//        service.sendEmail(user);
//    }

}