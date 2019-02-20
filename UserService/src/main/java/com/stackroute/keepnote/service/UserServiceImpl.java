package com.stackroute.keepnote.service;

import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.exceptions.UserAlreadyExistsException;
import com.stackroute.keepnote.exceptions.UserNotFoundException;
import com.stackroute.keepnote.model.User;
import com.stackroute.keepnote.repository.UserRepository;

/*
* Service classes are used here to implement additional business logic/validation 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn't currently 
* provide any additional behavior over the @Component annotation, but it's a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */
@Service
public class UserServiceImpl implements UserService {

	/*
	 * Autowiring should be implemented for the UserRepository. (Use
	 * Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */

	/*
	 * This method should be used to save a new user.Call the corresponding method
	 * of Respository interface.
	 */
	@Autowired
	UserRepository repository;

	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	public User registerUser(User user) throws UserAlreadyExistsException {
		User savedUser = null;
		if (repository.existsById(user.getUserId())) {
			throw new UserAlreadyExistsException("User with ID" + user.getUserId() + "already exists");
		} else {
			user.setUserAddedDate(new Date());
			savedUser = repository.insert(user);
			if (savedUser == null) {
				throw new UserAlreadyExistsException("User with ID" + user.getUserId() + "already exists");
			}
		}
		return savedUser;
	}

	/*
	 * This method should be used to update a existing user.Call the corresponding
	 * method of Respository interface.
	 */

	public User updateUser(String userId, User user) throws UserNotFoundException {

		try {
			User fecthedUser = repository.findById(userId).get();
			fecthedUser.setUserName(user.getUserName());
			fecthedUser.setUserMobile(user.getUserMobile());
			fecthedUser.setUserPassword(user.getUserPassword());
			fecthedUser.setUserId(user.getUserId());

			repository.save(fecthedUser);
			return fecthedUser;

		} catch (NoSuchElementException exception) {

			throw new UserNotFoundException("User does not exists");
		}

	}

	/*
	 * This method should be used to delete an existing user. Call the corresponding
	 * method of Respository interface.
	 */

	public boolean deleteUser(String userId) throws UserNotFoundException {
		boolean status = false;
		try {
			User fecthedUser = repository.findById(userId).get();
			if (fecthedUser != null) {
				repository.delete(fecthedUser);
				status = true;
			}
		} catch (NoSuchElementException exception) {
			throw new UserNotFoundException("User does not exists");
		}
		return status;
	}

	/*
	 * This method should be used to get a user by userId.Call the corresponding
	 * method of Respository interface.
	 */

	public User getUserById(String userId) throws UserNotFoundException {
		User fecthedUser = repository.findById(userId).get();
		if (fecthedUser == null) {
			throw new UserNotFoundException("User does not exists");
		}
		return fecthedUser;
	}

}
