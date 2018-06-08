package com.stackroute.keepnote.exception;

public class NoteAlreadyExistsException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public NoteAlreadyExistsException(String message)
	{
		super(message);
	}
}
