package com.microservices.classroomutility.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "[User]")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "UserId")
    @Id
    private Long userId;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "Mobile")
    private String mobile;

    @Column(name = "Role")
    private String role;

    @Column(name = "Status")
    private Integer status;

}