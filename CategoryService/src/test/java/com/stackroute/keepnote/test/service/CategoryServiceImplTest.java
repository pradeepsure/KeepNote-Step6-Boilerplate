package com.stackroute.keepnote.test.service;

import com.stackroute.keepnote.exception.CategoryNotCreatedException;
import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.model.Category;
import com.stackroute.keepnote.repository.CategoryRepository;
import com.stackroute.keepnote.service.CategoryServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;

public class CategoryServiceImplTest {

    private Category category;
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;
    private List<Category> allCategories = null;
    Optional<Category> options;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        category = new Category();

        category.setId("5b04f7411764e3765c35f8f6");
        category.setCategoryName("Cricket-Category");
        category.setCategoryDescription("All about Cricket");
        category.setCategoryCreatedBy("Jhon123");
        category.setCategoryCreationDate(new Date());

        allCategories = new ArrayList<>();
        allCategories.add(category);
        options = Optional.of(category);
    }

    @Test
    public void createCategoryTestSuccess() throws Exception {

        when(categoryRepository.insert((Category) any())).thenReturn(category);
        Category savedCategory = categoryServiceImpl.createCategory(category);
        Assert.assertEquals(category, savedCategory);

    }

    @Test(expected = CategoryNotCreatedException.class)
    public void createCategoryTestFailure() throws Exception {

        when(categoryRepository.insert((Category) any())).thenReturn(null);
        Category savedCategory = categoryServiceImpl.createCategory(category);
        Assert.assertEquals(category, savedCategory);

    }

    @Test
    public void deleteCategory() throws Exception {
        when(categoryRepository.findById(category.getId())).thenReturn(options);
        boolean flag = categoryServiceImpl.deleteCategory(category.getId());
        Assert.assertEquals(true, flag);
    }




    @Test
    public void updateCategoryTestSuccess() {
        when(categoryRepository.findById(category.getId())).thenReturn(options);
        category.setCategoryDescription("All about cricket and other sports");
        Category fetchedCategory = categoryServiceImpl.updateCategory(category, category.getId());
        Assert.assertEquals(category, fetchedCategory);

    }

    @Test
    public void updateCategoryTestFailure() {
        when(categoryRepository.findById(category.getId())).thenReturn(options);
        category.setCategoryDescription("All about cricket and other sports");
        Category fetchedCategory = categoryServiceImpl.updateCategory(category, category.getId());
        Assert.assertEquals(category, fetchedCategory);

    }


    @Test
    public void getCategoryByIdTestSuccess() throws CategoryNotFoundException {
        when(categoryRepository.findById(category.getId())).thenReturn(options);
        Category fetchedCategory = categoryServiceImpl.getCategoryById(category.getId());
        Assert.assertEquals(category, fetchedCategory);

    }

    @Test(expected = CategoryNotFoundException.class)
    public void getCategoryByIdTestFailure() throws CategoryNotFoundException {
        when(categoryRepository.findById(category.getId())).thenThrow(NoSuchElementException.class);
        Category fetchedCategory = categoryServiceImpl.getCategoryById(category.getId());
        Assert.assertEquals(category, fetchedCategory);

    }


    @Test
    public void getAllCategoryByUserIdTestSuccess() {
        when(categoryRepository.findAllCategoryByCategoryCreatedBy(category.getCategoryCreatedBy())).thenReturn(allCategories);
        List<Category> categories = categoryServiceImpl.getAllCategoryByUserId("Jhon123");
        Assert.assertEquals(allCategories, categories);
    }
}
