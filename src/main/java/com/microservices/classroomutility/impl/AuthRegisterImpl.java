package com.microservices.classroomutility.impl;

import com.microservices.classroomutility.entity.DBUserInterface;
import com.microservices.classroomutility.exception.ValidationException;
import com.microservices.classroomutility.repo.UserRepo;
import com.microservices.classroomutility.service.AuthRegisterService;
import com.microservices.classroomutility.util.DBQuery;
import com.microservices.classroomutility.vo.EmailTemplate;
import com.microservices.classroomutility.vo.GenericResponse;
import com.microservices.classroomutility.entity.User;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.Service;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
@NoArgsConstructor

public class AuthRegisterImpl implements AuthRegisterService {

    @Autowired
    private UserRepo userRepo;
    private DBQuery dbQuery;
    private String generatedOtp;
    private String forEmail;
    @Autowired
    public JavaMailSender javaMailSender;

    @Override
    public GenericResponse createUser(User user) {
        int rowsUpdated;
        rowsUpdated = userRepo.insertUser(user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getPassword(), Long.parseLong(user.getMobile()), user.getRole(), user.getStatus());
        if(rowsUpdated > 0){
            return new GenericResponse("SUCCESS");
        }
        throw new ValidationException("USER_NOT_CREATED");
    }

    @Override
    public GenericResponse loginUser(User user) {
        //String checkEmail = user.getEmail();
        DBUserInterface tempUser = userRepo.loginUser(user.getEmail()); //UI ka User
        if (tempUser != null){
            if(tempUser.getpassword().equals(user.getPassword())) {
                return new GenericResponse("SUCCESS");
            }
            else {
                throw new ValidationException("INVALID_USERNAME_PASSWORD");
            }
        }
        throw new ValidationException("Please Signup!");
    }

    @Override
    public GenericResponse forgetPassword(User user) throws UnsupportedEncodingException, MessagingException {
//        DBUserInterface tempUser = userRepo.loginUser(user.getEmail());
//        if (tempUser != null){
//            sendEmail(tempUser);
//            return new GenericResponse("OTP_SENT.");
//        }
//        throw new ValidationException("USER_DOESN'T_EXISTS");
        String generatedOtp = DBQuery.generateOTP();
        sendEmail(user.getEmail(), user.getFirstName(),generatedOtp);
        return new GenericResponse(generatedOtp);
    }

    @Override
    public GenericResponse otpVerification(String otp){
        if (otp.equals(generatedOtp)){
            return new GenericResponse("OTP_VERIFICATION_SUCCESSFUL.");
        }
        throw new ValidationException("VERIFICATION_UNSUCCESSFUL.");
    }

    @Override
    public GenericResponse updatePassword(User user, String updatedPassword){
        if (forEmail.equals(user.getEmail())){//
            int rowsUpdated;
            rowsUpdated = userRepo.updatePassword(updatedPassword,user.getEmail());
            if(rowsUpdated > 0){
                return new GenericResponse("SUCCESS");
            }
        }
        throw new ValidationException("PASSWORD_UPDATION_FAILED");
    }

    public void sendEmail(String userEmail,String firstName,String generatedOtp) throws MessagingException, UnsupportedEncodingException {
        System.out.println("Entered");
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage);
        //SimpleMailMessage mailMessage = new SimpleMailMessage();

//        String mailSubject = "Upat Classroom has sent a message. ";
//        String mailContent = "<p><b> Sender Name : </b> Upat Classroom </p>";
//        mailContent += "<p> This is text message </p>";
//        mailContent += "<p><b> Sender Address : </b> upat.classroom@gmail.com </p>";
//
//        helper.setFrom("rucha2498@gmail.com","Upat Classroom");
//        helper.setTo("rucha6350@gmail.com");
//        helper.setSubject(mailSubject);
//        helper.setText(mailContent,true);
//        javaMailSender.send(mailMessage);
        //String username = user.getFirstName() + " " + user.getLastName()


        System.out.println(generatedOtp);
        //Generate The Template to send OTP
        EmailTemplate template = new EmailTemplate("otpEmail.html");
        Map<String,String> replacements = new HashMap<String,String>();
        replacements.put("username", firstName);
        replacements.put("generatedOtp", String.valueOf(generatedOtp));
        String message = template.getTemplate(replacements);

        helper.setFrom("rucha2498@gmail.com","Upat Classroom");
        helper.setTo(userEmail);
        helper.setSubject("Message from Upat Classroom");
        helper.setText(message,true);
        javaMailSender.send(mailMessage);
        System.out.println("Email sent successfully");
        //return new GenericResponse("SUCCESS");
    }
}