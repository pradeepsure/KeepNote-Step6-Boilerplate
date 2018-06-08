package com.stackroute.keepnote.test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.stackroute.keepnote.exception.ReminderNotCreatedException;
import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Reminder;
import com.stackroute.keepnote.repository.ReminderRepository;
import com.stackroute.keepnote.service.ReminderServiceImpl;

import junit.framework.Assert;

public class ReminderServiceImplTest {

    @Mock
    ReminderRepository reminderRepository;

    Reminder reminder;

    @InjectMocks
    ReminderServiceImpl reminderService;

    List<Reminder> reminderList;
    Optional<Reminder> options;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        reminder = new Reminder();

        reminder.setReminderCreatedBy("John123");
        reminder.setReminderCreationDate(new Date());
        reminder.setReminderDescription("Sending Emails");
        reminder.setReminderId("5b0509731764e3096984eae6");
        reminder.setReminderName("Email");
        reminder.setReminderType("Email type");
        reminderList = new ArrayList<>();
        reminderList.add(reminder);

        options = Optional.of(reminder);

    }

    @Test
    public void createReminderSuccess() throws ReminderNotCreatedException
    {
        when(reminderRepository.insert((Reminder) any())).thenReturn(reminder);
        Reminder reminderSaved = reminderService.createReminder(reminder);
        Assert.assertEquals(reminder, reminderSaved);

    }

    @Test(expected = ReminderNotCreatedException.class)
    public void createReminderFailure() throws ReminderNotCreatedException
    {
        when(reminderRepository.insert((Reminder) any())).thenReturn(null);
        Reminder reminderSaved = reminderService.createReminder(reminder);
        Assert.assertEquals(reminder, reminderSaved);
    }

    @Test
    public void deleteReminderSuccess() throws ReminderNotFoundException
    {

        when(reminderRepository.findById(reminder.getReminderId())).thenReturn(options);
        boolean flag = reminderService.deleteReminder(reminder.getReminderId());
        Assert.assertEquals(true, flag);

    }

    @Test
    public void updateReminder() throws ReminderNotFoundException
    {

        when(reminderRepository.findById(reminder.getReminderId())).thenReturn(options);
        reminder.setReminderDescription("Send message at 6:00Pm");
        Reminder fetchedreminder = reminderService.updateReminder(reminder, reminder.getReminderId());
        Assert.assertEquals(reminder, fetchedreminder);

    }

    @Test
    public void getReminderByIdSuccess() throws ReminderNotFoundException
    {
        when(reminderRepository.findById(reminder.getReminderId())).thenReturn(options);
        Reminder fetchedReminder = reminderService.getReminderById(reminder.getReminderId());
        Assert.assertEquals(reminder,fetchedReminder );

    }


    @Test
    public void getAllReminders()
    {
        when(reminderRepository.findAll()).thenReturn(reminderList);
        List<Reminder> reminderListdata = reminderService.getAllReminders();
        Assert.assertEquals(reminderList, reminderListdata);

    }














}