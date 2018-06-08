package com.stackroute.keepnote.test.repository;

import com.stackroute.keepnote.model.Category;
import com.stackroute.keepnote.repository.CategoryRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    private Category category;

    @Before
    public void setUp() throws Exception {
        category = new Category();
        category.setId("5b04f7411764e3765c35f8f6");
        category.setCategoryName("Cricket-Category");
        category.setCategoryDescription("All about Cricket");
        category.setCategoryCreatedBy("Jhon123");
        category.setCategoryCreationDate(new Date());
    }

    @After
    public void tearDown() throws Exception {

        categoryRepository.deleteAll();
    }

    @Test
    public void createCategoryTest() {

        categoryRepository.insert(category);
        Category fetchedCategory = categoryRepository.findById(category.getId()).get();
        Assert.assertEquals("5b04f7411764e3765c35f8f6", fetchedCategory.getId());

    }

    @Test(expected = NoSuchElementException.class)
    public void deleteCategorytest() {

        categoryRepository.insert(category);
        Category fetchedCategory = categoryRepository.findById(category.getId()).get();
        Assert.assertEquals("5b04f7411764e3765c35f8f6", fetchedCategory.getId());
        categoryRepository.delete(fetchedCategory);
        fetchedCategory = categoryRepository.findById(category.getId()).get();

    }

    @Test
    public void updateCategoryTest() {

        categoryRepository.insert(category);
        Category fetchedCategory = categoryRepository.findById(category.getId()).get();
        Assert.assertEquals("5b04f7411764e3765c35f8f6", fetchedCategory.getId());
        fetchedCategory.setCategoryDescription("All about T-20");
        categoryRepository.save(fetchedCategory);
        fetchedCategory = categoryRepository.findById(category.getId()).get();
        Assert.assertEquals("All about T-20", fetchedCategory.getCategoryDescription());


    }

    @Test
    public void getCategoryByIdTest() {

        categoryRepository.insert(category);
        Category fetchedCategory = categoryRepository.findById(category.getId()).get();
        Assert.assertEquals("5b04f7411764e3765c35f8f6", fetchedCategory.getId());
    }

    @Test
    public void getAllCategoryByUserId() {

        categoryRepository.insert(category);

        category.setId("5b04f7411764e3765c35f8c4");
        category.setCategoryName("Badminton-Category");
        category.setCategoryDescription("All about Badminton");
        category.setCategoryCreatedBy("Jhon123");
        category.setCategoryCreationDate(new Date());

        categoryRepository.insert(category);

        List<Category> categories = categoryRepository.findAllCategoryByCategoryCreatedBy("Jhon123");
        Assert.assertEquals(2, categories.size());


    }
}
