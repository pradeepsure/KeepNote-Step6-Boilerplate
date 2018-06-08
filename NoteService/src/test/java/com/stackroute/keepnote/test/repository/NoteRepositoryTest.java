package com.stackroute.keepnote.test.repository;

import com.stackroute.keepnote.model.Category;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.model.NoteUser;
import com.stackroute.keepnote.model.Reminder;
import com.stackroute.keepnote.repository.NoteRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    private Note note;
    private Category category;
    private Reminder reminder;
    private NoteUser noteUser;
    private List<Note> noteList = null;


    @Before
    public void setUp() throws Exception {

        category = new Category();
        category = new Category();
        category.setCategoryId("5b04f7411764e3765c35f8f6");
        category.setCategoryName("Cricket-Category");
        category.setCategoryDescription("All about Cricket");
        category.setCategoryCreatedBy("Jhon123");
        category.setCategoryCreationDate(new Date());

        // Reminder

        reminder = new Reminder();
        reminder.setReminderId("5b0509731764e3096984eae6");
        reminder.setReminderName("Email-Reminder");
        reminder.setReminderDescription("sending emails");
        reminder.setReminderType("email type");
        reminder.setReminderCreatedBy("Jhon123");
        reminder.setReminderCreationDate(new Date());


        List<Reminder> reminderList = new ArrayList<>();
        reminderList.add(reminder);

        // Note
        note = new Note();
        note.setNoteId(1);
        note.setNoteTitle("IPL lists");
        note.setNoteContent("Mumbai Indians vs RCB match scheduled  for 4 PM");
        note.setNoteStatus("Active");
        note.setCategory(category);
        note.setReminders(reminderList);
        note.setNoteCreatedBy("Jhon123");
        note.setNoteCreationDate(new Date());

        noteList = new ArrayList<>();
        noteList.add(note);

        noteUser = new NoteUser();
        noteUser.setUserId("Jhon123");
        noteUser.setNotes(noteList);

    }

    @After
    public void tearDown() throws Exception {

        noteRepository.deleteAll();
    }

    @Test
    public void createNoteTest() {
        noteRepository.insert(noteUser);
        List<Note> allNotes = noteRepository.findById("Jhon123").get().getNotes();
        Assert.assertEquals(noteList.get(0).getNoteId(), allNotes.get(0).getNoteId());
    }


    @Test
    public void deleteNoteTest() {
        noteRepository.insert(noteUser);
        List<Note> allNotes = noteRepository.findById("Jhon123").get().getNotes();
        Assert.assertEquals(noteList.get(0).getNoteId(), allNotes.get(0).getNoteId());
        Iterator iterator = allNotes.listIterator();
        while (iterator.hasNext()) {
            note = (Note) iterator.next();
            if (note.getNoteId() == 1)
                iterator.remove();
        }

        noteUser.setNotes(allNotes);
        noteRepository.save(noteUser);

        allNotes = noteRepository.findById("Jhon123").get().getNotes();

        Assert.assertEquals(true, allNotes.isEmpty());

    }


    @Test
    public void updateNoteTest() {

        noteRepository.insert(noteUser);
        List<Note> allNotes = noteRepository.findById("Jhon123").get().getNotes();
        Assert.assertEquals(noteList.get(0).getNoteId(), allNotes.get(0).getNoteId());
        Iterator iterator = allNotes.listIterator();
        while (iterator.hasNext()) {
            note = (Note) iterator.next();
            if (note.getNoteId() == 1)
                note.setNoteContent("Mumbai Indians vs RCB match scheduled  for 4 PM is cancelled");
        }
        noteUser.setNotes(allNotes);
        noteRepository.save(noteUser);
        allNotes = noteRepository.findById("Jhon123").get().getNotes();
        Assert.assertEquals("Mumbai Indians vs RCB match scheduled  for 4 PM is cancelled", allNotes.get(0).getNoteContent());
    }

    @Test
    public void getAllNotesByUserId() {

        noteRepository.insert(noteUser);
        List<Note> allNotes = noteRepository.findById("Jhon123").get().getNotes();
        Assert.assertEquals(1, allNotes.size());
    }
}
