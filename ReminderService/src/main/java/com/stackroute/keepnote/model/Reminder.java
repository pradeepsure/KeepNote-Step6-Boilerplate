package com.stackroute.keepnote.model;

import java.util.Date;

/*
 * Please note that this class is annotated with @Document annotation
 * @Document identifies a domain object to be persisted to MongoDB.
 *  */
public class Reminder {

	/*
	 * This class should have six fields
	 * (reminderId,reminderName,reminderDescription,reminderType,
	 * reminderCreatedBy,reminderCreationDate). Out of these six fields, the field
	 * reminderId should be annotated with @Id. This class should also contain the
	 * getters and setters for the fields along with the no-arg , parameterized
	 * constructor and toString method. The value of reminderCreationDate should not
	 * be accepted from the user but should be always initialized with the system
	 * date.
	 */

	    public String getReminderId() {
	        return null;
	    }

	    public void setReminderId(String reminderId) {
	        
	    }

	    public String getReminderName() {
	        return null;
	    }

	    public void setReminderName(String reminderName) {
	      
	    }

	    public String getReminderDescription() {
	        return null;
	    }

	    public void setReminderDescription(String reminderDescription) {
	        
	    }

	    public String getReminderType() {
	        return null;
	    }

	    public void setReminderType(String reminderType) {
	       
	    }

	    public String getReminderCreatedBy() {
	        return null;
	    }

	    public void setReminderCreatedBy(String reminderCreatedBy) {
	      
	    }

	    public Date getReminderCreationDate() {
	        return null;
	    }

	    public void setReminderCreationDate(Date reminderCreationDate) {
	       
	    }

}
