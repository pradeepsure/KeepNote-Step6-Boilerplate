package com.stackroute.keepnote.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User {

    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String userPassword;
    private String userRole;
    private Date userAddedDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }


    public Date getUserAddedDate() {
        return userAddedDate;
    }

    public void setUserAddedDate(Date userAddedDate) {
        this.userAddedDate = userAddedDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userAddedDate=" + userAddedDate +
                '}';
    }
}