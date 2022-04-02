package com.microservices.classroomutility.util;

public final class DBQuery {

    public static final String insertUserQuery
            = "insert into [User] (FirstName, LastName, Email, Password, Mobile, Role, Status) " +
            "values (?, ?, ?, ?, ?, ?, ?)";

    public static final String loginUserQuery
            = "Select * from [User] " +
            "WHERE Email = ?";
}