package com.stackroute.keepnote.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.keepnote.controller.CategoryController;
import com.stackroute.keepnote.exception.CategoryDoesNoteExistsException;
import com.stackroute.keepnote.exception.CategoryNotCreatedException;
import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.model.Category;
import com.stackroute.keepnote.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Category category;
    @MockBean
    private CategoryService categoryService;
    @InjectMocks
    private CategoryController categoryController;
    private List<Category> allCategories = null;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
        category = new Category();

        category.setId("5b04f7411764e3765c35f8f6");
        category.setCategoryName("Cricket-Category");
        category.setCategoryDescription("All about Cricket");
        category.setCategoryCreatedBy("Jhon123");
        category.setCategoryCreationDate(new Date());

        allCategories = new ArrayList<>();
        allCategories.add(category);


    }

    @Test
    public void createCategorySuccess() throws Exception {

        when(categoryService.createCategory(any())).thenReturn(category);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/category")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(category)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void createCategoryFailure() throws Exception {

        when(categoryService.createCategory(any())).thenThrow(CategoryNotCreatedException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/category")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(category)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void deleteCategorySuccess() throws Exception {

        when(categoryService.deleteCategory("5b04f7411764e3765c35f8f6")).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/category/5b04f7411764e3765c35f8f6")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void deleteCategoryFailure() throws Exception {

        when(categoryService.deleteCategory("5b04f7411764e3765c35f8f6")).thenThrow(CategoryDoesNoteExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/category/5b04f7411764e3765c35f8f6")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void updateCategorySuccess() throws Exception {

        when(categoryService.updateCategory(any(), eq(category.getId()))).thenReturn(category);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/category/5b04f7411764e3765c35f8f6")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(category)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateCategoryFailure() throws Exception {

        when(categoryService.updateCategory(any(), eq(category.getId()))).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/category/5b04f7411764e3765c35f8f6")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(category)))
                .andExpect(MockMvcResultMatchers.status().isConflict()).andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void getCategoryByIdSuccess() throws Exception {
        when(categoryService.getCategoryById(category.getId())).thenReturn(category);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/category/5b04f7411764e3765c35f8f6")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void getCategoryByIdFailure() throws Exception {
        when(categoryService.getCategoryById(category.getId())).thenThrow(CategoryNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/category/5b04f7411764e3765c35f8f6")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }



    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
