package com.stackroute.keepnote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.keepnote.exception.ReminderNotCreatedException;
import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Reminder;
import com.stackroute.keepnote.service.ReminderService;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation.A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 */

@RestController
@RequestMapping("/api/v1/reminder")
public class ReminderController {

	/*
	 * From the problem statement, we can understand that the application requires
	 * us to implement five functionalities regarding reminder. They are as
	 * following:
	 * 
	 * 1. Create a reminder 
	 * 2. Delete a reminder 
	 * 3. Update a reminder 
	 * 4. Get all reminders by userId 
	 * 5. Get a specific reminder by id.
	 * 
	 */

	/*
	 * Autowiring should be implemented for the ReminderService. (Use
	 * Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword
	 */
	
	@Autowired
	ReminderService service;

	public ReminderController(ReminderService reminderService) {
		this.service=reminderService;
	}

	/*
	 * Define a handler method which will create a reminder by reading the
	 * Serialized reminder object from request body and save the reminder in
	 * database. Please note that the reminderId has to be unique. This handler
	 * method should return any one of the status messages basis on different
	 * situations: 
	 * 1. 201(CREATED - In case of successful creation of the reminder
	 * 2. 409(CONFLICT) - In case of duplicate reminder ID
	 *
	 * This handler method should map to the URL "/api/v1/reminder" using HTTP POST
	 * method".
	 */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Reminder reminder) {
		try {
			service.createReminder(reminder);
			return new ResponseEntity<String>("Created", HttpStatus.CREATED);
		} catch (ReminderNotCreatedException e) {
			return new ResponseEntity<String>("Conflict", HttpStatus.CONFLICT);
		}
	}

	/*
	 * Define a handler method which will delete a reminder from a database.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the reminder deleted successfully from database. 
	 * 2. 404(NOT FOUND) - If the reminder with specified reminderId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/reminder/{id}" using HTTP Delete
	 * method" where "id" should be replaced by a valid reminderId without {}
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		try {
			service.deleteReminder(id);
			return new ResponseEntity<String>("deleted", HttpStatus.OK);
		} catch (ReminderNotFoundException e) {
			return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * Define a handler method which will update a specific reminder by reading the
	 * Serialized object from request body and save the updated reminder details in
	 * a database. This handler method should return any one of the status messages
	 * basis on different situations: 
	 * 1. 200(OK) - If the reminder updated successfully. 
	 * 2. 404(NOT FOUND) - If the reminder with specified reminderId is not found. 
	 * 
	 * This handler method should map to the URL "/api/v1/reminder/{id}" using HTTP PUT
	 * method.
	 */
	@PutMapping("{id}")
	public ResponseEntity<?> update(@RequestBody Reminder reminder, @PathVariable String id) {
		try {
			return new ResponseEntity<Reminder>(service.updateReminder(reminder, id), HttpStatus.OK);
		} catch (ReminderNotFoundException e) {
			return new ResponseEntity<String>(": not found", HttpStatus.NOT_FOUND);
		}		
	}

	/*
	 * Define a handler method which will show details of a specific reminder. This
	 * handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the reminder found successfully. 
	 * 2. 404(NOT FOUND) - If the reminder with specified reminderId is not found. 
	 * 
	 * This handler method should map to the URL "/api/v1/reminder/{id}" using HTTP GET method
	 * where "id" should be replaced by a valid reminderId without {}
	 */
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable String id) {
		try {
			Reminder reminder = service.getReminderById(id);
			return new ResponseEntity<Reminder>(reminder, HttpStatus.OK);
		} catch (ReminderNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	

	/*
	 * Define a handler method which will get us the all reminders.
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the reminder found successfully. 
	 * 2. 404(NOT FOUND) - If the reminder with specified reminderId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/reminder" using HTTP GET method
	 */
	@GetMapping
	public ResponseEntity<?> get() {
		return new ResponseEntity<List<Reminder>>(service.getAllReminders(), HttpStatus.OK);
	}
}
