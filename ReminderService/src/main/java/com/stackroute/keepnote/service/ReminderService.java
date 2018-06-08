package com.stackroute.keepnote.service;

import com.stackroute.keepnote.exception.ReminderNotCreatedException;
import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Reminder;

import java.util.List;

public interface ReminderService {
	
	/*
	 * Should not modify this interface. You have to implement these methods in
	 * corresponding Impl classes
	 */

    Reminder createReminder(Reminder reminder) throws ReminderNotCreatedException;

    boolean deleteReminder(String reminderId) throws ReminderNotFoundException;

    Reminder updateReminder(Reminder reminder, String reminderId) throws ReminderNotFoundException;

    Reminder getReminderById(String reminderId) throws ReminderNotFoundException;

    List<Reminder> getAllReminders();
}
