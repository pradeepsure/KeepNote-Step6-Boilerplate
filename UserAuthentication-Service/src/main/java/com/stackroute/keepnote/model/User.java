package com.stackroute.keepnote.model;

import java.util.Date;

/*
 * The class "User" will be acting as the data model for the User Table in the database. 
 * Please note that this class is annotated with @Entity annotation. 
 * Hibernate will scan all package for any Java objects annotated with the @Entity annotation. 
 * If it finds any, then it will begin the process of looking through that particular 
 * Java object to recreate it as a table in your database.
 */

public class User {

    /*
	 * This class should have five fields (userId,firstName,lastName,
	 * userPassword,userRole,userAddedDate). Out of these five fields, the field
	 * userId should be the primary key. This class should also contain the getters
	 * and setters for the fields, along with the no-arg , parameterized constructor
	 * and toString method.The value of userAddedDate should not be accepted from
	 * the user but should be always initialized with the system date
	 */
	

    
    private String userId;
    private String userPassword;
   
	
    public String getUserId() {
    	return null;
    }

    public void setUserId(String  string) {
       
    }

    public String getFirstName() {
    	return null;
    }

    public void setFirstName(String  string) {
        
    }

    public String getLastName() {
    	return null;
    }

    public void setLastName(String  string) {
       
    }

    public String getUserPassword() {
    	return null;
    }

    public void setUserPassword(String  string) {
       
    }

    public String getUserRole() {
    	return null;
    }

    public void setUserRole(String  string) {
      
    }


    public Date getUserAddedDate() {
        return null;
    }

    public void setUserAddedDate(Date date) {
        
    }

    


}
