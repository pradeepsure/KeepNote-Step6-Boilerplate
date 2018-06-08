package com.stackroute.keepnote.exception;

public class CategoryDoesNoteExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public CategoryDoesNoteExistsException(String message) {
        super(message);
    }
}
