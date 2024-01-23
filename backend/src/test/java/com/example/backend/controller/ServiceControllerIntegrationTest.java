package com.example.backend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.backend.dto.category.CategoryDto;
import com.example.backend.dto.category.CategoryRequestDto;
import com.example.backend.dto.master.MasterDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceControllerIntegrationTest {

    protected static MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll(
            @Autowired WebApplicationContext applicationContext
    ) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();
    }

    @Test
    @DisplayName("Get list of existing in DB services - expected result: return list of services")
    public void getAllServices_Ok() throws Exception {
        //When
        MvcResult result = mockMvc.perform(
                        get("/services"))
                .andExpect(status().isOk())
                .andReturn();
        List<MasterDto> actual = objectMapper.readValue(result
                .getResponse().getContentAsString(), new TypeReference<>() {
                });
        //Then
        assertNotNull(actual);
        assertEquals(5, actual.size());
    }

    @Test
    @Sql(scripts = {
            "classpath:database/ServiceControllerIntegrationTest/after/"
                    + "after_createService_ValidRequestDto_Ok.sql",
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Create order - expected result: create order in DB, return created order")
    public void createService_ValidRequestDto_Ok() throws Exception {
        //Given
        CategoryRequestDto requestDto = createDefaultOrder();
        CategoryDto expected = getResponseDto(requestDto);
        String jsonRequest = objectMapper.writeValueAsString(requestDto);
        //When
        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.post("/services")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andReturn();

        CategoryDto actual = objectMapper.readValue(result.getResponse()
                .getContentAsString(), CategoryDto.class);
        //Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Create service with invalid name"
            + " expected result: return exception")
    public void createOrder_InValidRequestDto_NotOk() throws Exception {
        //Given
        CategoryRequestDto requestDto = createDefaultOrder();
        requestDto.setName("+++++");

        String jsonRequest = objectMapper.writeValueAsString(requestDto);
        //When
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/services")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = {
            "classpath:database/ServiceControllerIntegrationTest/before/"
                    + "after_deleteService_Ok.sql",
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/ServiceControllerIntegrationTest/after/"
                    + "after_createService_ValidRequestDto_Ok.sql",
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Delete order by id - expected result: delete order from DB")
    public void deleteService_Ok() throws Exception {
        mockMvc.perform(
                        delete("/services/{id}", 6))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Delete service by not existing in DB id - expected result: "
            + "return exception - NOT FOUND")
    public void deleteService_InvalidId_NotOk() throws Exception {
        mockMvc.perform(
                        delete("/services/{id}", 100L))
                .andExpect(status().isNotFound());
    }

    private static CategoryDto getResponseDto(CategoryRequestDto requestDto) {
        return new CategoryDto()
                .setId(6L)
                .setName(requestDto.getName());

    }

    private static CategoryRequestDto createDefaultOrder() {
        return new CategoryRequestDto()
                .setName("new category");
    }
}

