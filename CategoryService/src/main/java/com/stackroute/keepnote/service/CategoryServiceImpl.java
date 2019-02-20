package com.stackroute.keepnote.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.exception.CategoryDoesNoteExistsException;
import com.stackroute.keepnote.exception.CategoryNotCreatedException;
import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.model.Category;
import com.stackroute.keepnote.repository.CategoryRepository;

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
public class CategoryServiceImpl implements CategoryService {

	/*
	 * Autowiring should be implemented for the CategoryRepository. (Use
	 * Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	@Autowired
	CategoryRepository repository;

	public CategoryServiceImpl(CategoryRepository repository) {
		this.repository = repository;
	}

	/*
	 * This method should be used to save a new category.Call the corresponding
	 * method of Respository interface.
	 */
	public Category createCategory(Category category) throws CategoryNotCreatedException {
		Category cat = repository.insert(category);
		if (cat == null)
			throw new CategoryNotCreatedException("Cat not created");
		return cat;
	}

	/*
	 * This method should be used to delete an existing category.Call the
	 * corresponding method of Respository interface.
	 */
	public boolean deleteCategory(String categoryId) throws CategoryDoesNoteExistsException {
		if (!repository.findById(categoryId).isPresent()) {
			throw new CategoryDoesNoteExistsException("Category does not exist");
		}
		repository.deleteById(categoryId);
		return Boolean.TRUE;
	}

	/*
	 * This method should be used to update a existing category.Call the
	 * corresponding method of Respository interface.
	 */
	public Category updateCategory(Category category, String categoryId) {
		Category fetchedCategory = repository.findById(categoryId).get();
		// fetchedCategory.setCategoryId(fetchedCategory.getCategoryId());
		fetchedCategory.setCategoryName(category.getCategoryName());
		fetchedCategory.setCategoryDescription(category.getCategoryDescription());
		fetchedCategory.setCategoryCreatedBy(category.getCategoryCreatedBy());
		fetchedCategory.setCategoryCreationDate(new Date());
		repository.save(fetchedCategory);
		return fetchedCategory;
	}

	/*
	 * This method should be used to get a category by categoryId.Call the
	 * corresponding method of Respository interface.
	 */
	public Category getCategoryById(String categoryId) throws CategoryNotFoundException {
		try {
			return repository.findById(categoryId).get();
		} catch (NoSuchElementException e) {
			throw new CategoryNotFoundException("Category not found");
		}

	}

	/*
	 * This method should be used to get a category by userId.Call the corresponding
	 * method of Respository interface.
	 */
	public List<Category> getAllCategoryByUserId(String userId) {
		return repository.findAllCategoryByCategoryCreatedBy(userId);
	}

}
