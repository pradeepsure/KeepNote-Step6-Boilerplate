package com.stackroute.keepnote.test.controller;



import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.keepnote.controller.ReminderController;
import com.stackroute.keepnote.exception.ReminderNotCreatedException;
import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Reminder;
import com.stackroute.keepnote.service.ReminderService;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ReminderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Reminder reminder;

    @MockBean
    private ReminderService reminderService;

    @InjectMocks
    private ReminderController reminderController;

    private List<Reminder> reminderList;


    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reminderController).build();
        reminder = new Reminder();
        reminder.setReminderCreatedBy("John123");
        reminder.setReminderCreationDate(new Date());
        reminder.setReminderDescription("Sending Emails");
        reminder.setReminderId("5b0509731764e3096984eae6");
        reminder.setReminderName("Email");
        reminder.setReminderType("Email type");
        reminderList = new ArrayList<>();
        reminderList.add(reminder);


    }

    @Test
    public void createReminderSuccess() throws Exception
    {
        when(reminderService.createReminder(any())).thenReturn(reminder);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/reminder").contentType
                (MediaType.APPLICATION_JSON)
                .content(asJsonString(reminder)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void createReminderFailure() throws Exception
    {
        when(reminderService.createReminder(any())).thenThrow(ReminderNotCreatedException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/reminder").
                contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(reminder)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void deleteReminderSuccess() throws Exception
    {
        when(reminderService.deleteReminder(reminder.getReminderId())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/reminder/5b0509731764e3096984eae6").
                contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void deleteReminderFailure() throws Exception
    {
        when(reminderService.deleteReminder(reminder.getReminderId())).thenThrow(ReminderNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/reminder/5b0509731764e3096984eae6").
                contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());


    }



    @Test
    public void updateReminderSuccess() throws Exception {

        when(reminderService.updateReminder(any(), eq(reminder.getReminderId()))).thenReturn(reminder);
        reminder.setReminderDescription("Send me email at 6:00PM");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/reminder/5b0509731764e3096984eae6")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(reminder)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void updateReminderFailure() throws Exception
    {
        when(reminderService.updateReminder(any(),eq(reminder.getReminderId()))).thenThrow(ReminderNotFoundException.class);
        reminder.setReminderDescription("Send me email at 6:00PM");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/reminder/5b0509731764e3096984eae6")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(reminder)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getReminderByIdSucccess() throws Exception
    {
        when(reminderService.getReminderById(reminder.getReminderId())).thenReturn(reminder);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reminder/5b0509731764e3096984eae6")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(reminder))).
                andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getReminderByIdFailure() throws Exception
    {
        when(reminderService.getReminderById(reminder.getReminderId())).thenThrow(ReminderNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reminder/5b0509731764e3096984eae6").
                contentType(MediaType.APPLICATION_JSON).content(asJsonString(reminder)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getAllReminderById() throws Exception
    {
        when(reminderService.getAllReminders()).thenReturn(reminderList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reminder").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(reminderList))).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}