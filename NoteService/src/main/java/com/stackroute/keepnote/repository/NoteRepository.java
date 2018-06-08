package com.stackroute.keepnote.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.model.NoteUser;

/*
* This class is implementing the MongoRepository interface for Note.
* Annotate this class with @Repository annotation
* */

public interface NoteRepository extends MongoRepository<NoteUser, String> {

}
