package com.example.backend.controller;

import com.example.backend.dto.category.CategoryDto;
import com.example.backend.dto.comment.CommentDto;
import com.example.backend.dto.comment.CommentRequestDto;
import com.example.backend.dto.service.ServiceDto;
import com.example.backend.dto.service.ServiceRequestDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SubServiceControllerIntegrationTest {
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
    @DisplayName("Get list of existing in DB subservices - expected result: return list of subservices")
    public void getAllSubServices_Ok() throws Exception {
        //When
        MvcResult result = mockMvc.perform(
                        get("/subservices"))
                .andExpect(status().isOk())
                .andReturn();
        List<ServiceDto> actual = objectMapper.readValue(result
                .getResponse().getContentAsString(), new TypeReference<>() {
        });
        //Then
        assertNotNull(actual);
        assertEquals(81, actual.size());
    }

    @Test
    @DisplayName("Get all subservices by specific service")
    public void getAllSubServicesByService_Ok() throws Exception {
        //Given
        String category = "nails";
        //When
        MvcResult result = mockMvc.perform(
                        get("/subservices/{category}", category)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        List<ServiceDto> actual = objectMapper.readValue(
                result.getResponse().getContentAsString(), new TypeReference<>() {
                }
        );

        //Then
        assertNotNull(actual);
        assertEquals(13, actual.size());
    }


    @Test
    @DisplayName("Find subservices by not existing service - expected result: "
            + "return exception - NOT FOUND")
    public void getAllCommentsByService_NotOk() throws Exception {
        //Given
        String unknownCategory = "unknown";
        //Then
        mockMvc.perform(get("/subservices/{category}", unknownCategory))
                .andExpect(status().isNotFound());
    }

    @Test
    @Sql(scripts = {
            "classpath:database/SubServiceControllerIntegrationTest/after/" +
                    "after_createSubService_ValidRequestDto_Ok.sql",
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Create subService - expected result: create subService in DB, " +
            "return created subService")
    public void createSubService_ValidRequestDto_Ok() throws Exception {
        //Given
        ServiceRequestDto requestDto = createDefaultSubService();
        ServiceDto expected = getResponseDto(requestDto);
        String jsonRequest = objectMapper.writeValueAsString(requestDto);
        //When
        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.post("/subservices")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andReturn();

        ServiceDto actual = objectMapper.readValue(result.getResponse()
                .getContentAsString(), ServiceDto.class);
        //Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Create subService with invalid name," +
            " expected result: return exception")
    public void createSubService_InValidRequestDto_NotOk() throws Exception {
        //Given
        ServiceRequestDto requestDto = createDefaultSubService();
        requestDto.setName("+++");
        String jsonRequest = objectMapper.writeValueAsString(requestDto);
        //When
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/subservices")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = {
            "classpath:database/SubServiceControllerIntegrationTest/before/" +
                    "before_deleteSubService_Ok.sql",
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @DisplayName("Delete subService by id - expected result: delete subService from DB")
    public void deleteSubService_Ok() throws Exception {
        mockMvc.perform(
                        delete("/subservices/{id}", 84L))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Delete subService by not existing in DB id - expected result: "
            + "return exception - NOT FOUND")
    public void deleteSubService_InvalidId_NotOk() throws Exception {
        mockMvc.perform(
                        delete("/subservices/{id}", 100L))
                .andExpect(status().isNotFound());
    }

    private static ServiceDto getResponseDto(ServiceRequestDto requestDto) {
        return new ServiceDto()
                .setId(83L)
                .setName(requestDto.getName())
                .setHeadMasterPrice(requestDto.getHeadMasterPrice())
                .setMasterPrice(requestDto.getMasterPrice());
    }

    private static ServiceRequestDto createDefaultSubService() {
        return new ServiceRequestDto()
                .setCategoryId(2L)
                .setName("new service")
                .setMasterPrice(BigDecimal.valueOf(500))
                .setHeadMasterPrice(BigDecimal.valueOf(1000));
    }
}
