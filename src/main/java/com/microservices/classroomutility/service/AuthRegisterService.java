package com.microservices.classroomutility.service;

import com.microservices.classroomutility.entity.DBUserInterface;
import com.microservices.classroomutility.vo.GenericResponse;
import com.microservices.classroomutility.entity.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface AuthRegisterService {

    GenericResponse createUser(User user);

    GenericResponse loginUser(User user);

    GenericResponse forgetPassword(User user) throws UnsupportedEncodingException, MessagingException;

    GenericResponse otpVerification(String otp);

    GenericResponse updatePassword(User user,String updatedPassword);

    //void sendEmail(DBUserInterface user) throws MessagingException, UnsupportedEncodingException;
}