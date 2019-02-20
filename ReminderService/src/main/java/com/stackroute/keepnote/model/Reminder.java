package com.stackroute.keepnote.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Please note that this class is annotated with @Document annotation
 * @Document identifies a domain object to be persisted to MongoDB.
 *  */
@Document
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

	@Id
	private String reminderId;
	private String reminderName;
	private String reminderDescription;
	private String reminderType;
	private String reminderCreatedBy;
	private Date reminderCreationDate;

	public String getReminderId() {
		return reminderId;
	}

	public void setReminderId(String reminderId) {
		this.reminderId = reminderId;
	}

	public String getReminderName() {
		return reminderName;
	}

	public void setReminderName(String reminderName) {
		this.reminderName = reminderName;
	}

	public String getReminderDescription() {
		return reminderDescription;
	}

	public void setReminderDescription(String reminderDescription) {
		this.reminderDescription = reminderDescription;
	}

	public String getReminderType() {
		return reminderType;
	}

	public void setReminderType(String reminderType) {
		this.reminderType = reminderType;
	}

	public String getReminderCreatedBy() {
		return reminderCreatedBy;
	}

	public void setReminderCreatedBy(String reminderCreatedBy) {
		this.reminderCreatedBy = reminderCreatedBy;
	}

	public Date getReminderCreationDate() {
		return reminderCreationDate;
	}

	public void setReminderCreationDate(Date reminderCreationDate) {
		this.reminderCreationDate = reminderCreationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reminderCreatedBy == null) ? 0 : reminderCreatedBy.hashCode());
		result = prime * result + ((reminderCreationDate == null) ? 0 : reminderCreationDate.hashCode());
		result = prime * result + ((reminderDescription == null) ? 0 : reminderDescription.hashCode());
		result = prime * result + ((reminderId == null) ? 0 : reminderId.hashCode());
		result = prime * result + ((reminderName == null) ? 0 : reminderName.hashCode());
		result = prime * result + ((reminderType == null) ? 0 : reminderType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reminder other = (Reminder) obj;
		if (reminderCreatedBy == null) {
			if (other.reminderCreatedBy != null)
				return false;
		} else if (!reminderCreatedBy.equals(other.reminderCreatedBy))
			return false;
		if (reminderCreationDate == null) {
			if (other.reminderCreationDate != null)
				return false;
		} else if (!reminderCreationDate.equals(other.reminderCreationDate))
			return false;
		if (reminderDescription == null) {
			if (other.reminderDescription != null)
				return false;
		} else if (!reminderDescription.equals(other.reminderDescription))
			return false;
		if (reminderId == null) {
			if (other.reminderId != null)
				return false;
		} else if (!reminderId.equals(other.reminderId))
			return false;
		if (reminderName == null) {
			if (other.reminderName != null)
				return false;
		} else if (!reminderName.equals(other.reminderName))
			return false;
		if (reminderType == null) {
			if (other.reminderType != null)
				return false;
		} else if (!reminderType.equals(other.reminderType))
			return false;
		return true;
	}

}
