package com.stackroute.keepnote.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Please note that this class is annotated with @Document annotation
 * @Document identifies a domain object to be persisted to MongoDB.
 *  */
@Document
public class NoteUser {

	/*
	 * This class should have two fields (userId, notes).Out of these two fields,
	 * the field userId should be annotated with @Id. This class should also contain
	 * the getters and setters for the fields.
	 */

    

    @Id
    private String userId;
    private List<Note> notes;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "NoteUser{" +
                "userId='" + userId + '\'' +
                ", notes=" + notes +
                '}';
    }
}
