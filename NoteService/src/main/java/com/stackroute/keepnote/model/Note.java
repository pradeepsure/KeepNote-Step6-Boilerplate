package com.stackroute.keepnote.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Note {

	/*
	 * This class should have eight fields
	 * (noteId,noteTitle,noteContent,noteStatus,createdAt,
	 * category,reminder,createdBy). This class should also contain the getters and
	 * setters for the fields along with the no-arg , parameterized constructor and
	 * toString method. The value of createdAt should not be accepted from the user
	 * but should be always initialized with the system date.
	 * 
	 */

	@Id
	private int noteId;
	private String noteTitle;
	private String noteContent;
	private String noteStatus;
	private Date noteCreationDate;
	private String noteCreatedBy;
	private Category category;
	private List<Reminder> reminders;

	// getters & setters

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public String getNoteStatus() {
		return noteStatus;
	}

	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;
	}

	public Date getNoteCreationDate() {
		return noteCreationDate;
	}

	public void setNoteCreationDate(Date noteCreationDate) {
		this.noteCreationDate = noteCreationDate;
	}

	public String getNoteCreatedBy() {
		return noteCreatedBy;
	}

	public void setNoteCreatedBy(String noteCreatedBy) {
		this.noteCreatedBy = noteCreatedBy;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Reminder> getReminders() {
		return reminders;
	}

	public void setReminders(List<Reminder> reminders) {
		this.reminders = reminders;
	}

	@Override
	public String toString() {
		return "Note{" + "noteId=" + noteId + ", noteTitle='" + noteTitle + '\'' + ", noteContent='" + noteContent
				+ '\'' + ", noteStatus='" + noteStatus + '\'' + ", noteCreationDate=" + noteCreationDate
				+ ", noteCreatedBy='" + noteCreatedBy + '\'' + ", category=" + category + ", reminders=" + reminders
				+ '}';
	}
}
