package com.example.demo.controller;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.exception.ModelNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.service.ICategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoryService service;
    @MockBean(name = "categoryMapper")
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;
    Category CATEGORIA_1 = new Category(1,"TV","Television",true);

    Category CATEGORIA_2 = new Category(2,"PSP","Play Station Portable",true);
    Category CATEGORIA_3 = new Category(3,"Books","Some books",true);
    CategoryDTO CATEGORIADTO_1 = new CategoryDTO(1,"TV","Television",true);

    CategoryDTO CATEGORIADTO_2 = new CategoryDTO(2,"PSP","Play Station Portable",true);
    CategoryDTO CATEGORIADTO_3 = new CategoryDTO(3,"Books","Some books",true);

    @Test
    void readAllTest() throws Exception{
        List<Category> categories = List.of(CATEGORIA_1,CATEGORIA_2,CATEGORIA_3);
        Mockito.when(service.findAll()).thenReturn(categories);
        Mockito.when(modelMapper.map(CATEGORIA_1, CategoryDTO.class)).thenReturn(CATEGORIADTO_1);
        Mockito.when(modelMapper.map(CATEGORIA_2, CategoryDTO.class)).thenReturn(CATEGORIADTO_2);
        Mockito.when(modelMapper.map(CATEGORIA_3, CategoryDTO.class)).thenReturn(CATEGORIADTO_3);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/categories")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[1].nameofCategory",is("PSP")));

    }

    @Test
    void readByIdTest() throws Exception{
        final int ID =1;
        Mockito.when(service.readById(ID)).thenReturn(CATEGORIA_1);
        Mockito.when(modelMapper.map(CATEGORIA_1, CategoryDTO.class)).thenReturn(CATEGORIADTO_1);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/categories/"+ID)
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameofCategory",is("TV")));
    }

    @Test
    void createTest() throws Exception{
        Mockito.when(service.save(any())).thenReturn(CATEGORIA_3);
        Mockito.when(modelMapper.map(CATEGORIA_3,CategoryDTO.class)).thenReturn(CATEGORIADTO_3);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CATEGORIADTO_3));


        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status", is(201)));
    }

    @Test
    void updateTest() throws Exception{
        final int ID = 2;
        Mockito.when(service.update(any(),any())).thenReturn(CATEGORIA_2);
        Mockito.when(modelMapper.map(CATEGORIA_2,CategoryDTO.class)).thenReturn(CATEGORIADTO_2);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/categories/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CATEGORIADTO_2));
        //33:07

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameofCategory", is("PSP")));
    }

    @Test
    void updateeRRORTest() throws Exception{
        final int ID = 99;
        Mockito.when(service.update(any(),any())).thenThrow(new ModelNotFoundException("ID NOT VALIDAD: " + ID));
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/categories/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CATEGORIADTO_2));

        mockMvc.perform(mockRequest)
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ModelNotFoundException));
    }

    @Test
    void deleteTest () throws Exception{
        final int ID = 1;
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/categories/" + ID)
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteErrorTest () throws Exception{
        final int ID = 23;

        Mockito.doThrow(new ModelNotFoundException("ID NOT FOUND: " + ID)).when(service).delete(ID);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/categories/" + ID)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ModelNotFoundException));
    }
}
