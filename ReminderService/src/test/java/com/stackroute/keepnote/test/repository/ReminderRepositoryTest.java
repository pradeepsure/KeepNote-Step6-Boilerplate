package com.stackroute.keepnote.test.repository;

import com.stackroute.keepnote.model.Reminder;
import com.stackroute.keepnote.repository.ReminderRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ReminderRepositoryTest {


    @Autowired
    private ReminderRepository reminderRepository;
    private Reminder reminder;

    @Before
    public void setUp() throws Exception {
        reminder = new Reminder();
        reminder.setReminderId("12sdcdc321xewxcw34");
        reminder.setReminderName("Email reminder");
        reminder.setReminderDescription("Sending email @ 5AM");
        reminder.setReminderType("Daily Reminder");
        reminder.setReminderCreatedBy("Jhon123");
        reminder.setReminderCreationDate(new Date());
    }

    @After
    public void tearDown() throws Exception {
        reminderRepository.deleteAll();
    }

    @Test
    public void createReminderTest() {

        reminderRepository.insert(reminder);
        Reminder reminder1 = reminderRepository.findById(reminder.getReminderId()).get();
        Assert.assertEquals(reminder.getReminderName(), reminder1.getReminderName());

    }

    @Test(expected = NoSuchElementException.class)
    public void deleteReminderTest() {


        reminderRepository.insert(reminder);
        Reminder reminder1 = reminderRepository.findById(reminder.getReminderId()).get();
        reminderRepository.delete(reminder1);
        reminder1 = reminderRepository.findById(reminder.getReminderId()).get();
    }

    @Test
    public void updateReminderTest() {
        reminderRepository.insert(reminder);
        Reminder reminder1 = reminderRepository.findById(reminder.getReminderId()).get();
        reminder1.setReminderDescription("Sending emails @ 10AM");
        reminderRepository.save(reminder1);
        Assert.assertEquals("Sending emails @ 10AM", reminder1.getReminderDescription());


    }

    @Test
    public void getReminderByIdTest() {
        reminderRepository.insert(reminder);
        Reminder reminder1 = reminderRepository.findById(reminder.getReminderId()).get();
        Assert.assertEquals("Sending email @ 5AM", reminder1.getReminderDescription());


    }

    @Test
    public void getAllReminderTest() {
        reminderRepository.insert(reminder);

        reminder = new Reminder();
        reminder.setReminderId("12sdcdc321ded33333");
        reminder.setReminderName("SMA reminder");
        reminder.setReminderDescription("Sending SMS @ 5AM");
        reminder.setReminderType("Daily Reminder");
        reminder.setReminderCreatedBy("Jhon123");
        reminder.setReminderCreationDate(new Date());
        reminderRepository.insert(reminder);

        List<Reminder> allReminders = reminderRepository.findAll();
        Assert.assertEquals(2, allReminders.size());

    }
}
