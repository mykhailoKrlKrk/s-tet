package com.example.backend.controller;

import com.example.backend.dto.comment.CommentDto;
import com.example.backend.dto.master.MasterDto;
import com.example.backend.dto.master.MasterRequestDto;
import com.example.backend.model.Qualification;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MasterControllerIntegrationTest {

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
    @DisplayName("Get list of existing in DB masters - expected result: return list of masters")
    public void getAllMasters_ReturnListOfExistingMasters() throws Exception {
        //When
        MvcResult result = mockMvc.perform(
                        get("/masters"))
                .andExpect(status().isOk())
                .andReturn();
        List<MasterDto> actual = objectMapper.readValue(result
                .getResponse().getContentAsString(), new TypeReference<>() {
        });
        //Then
        assertNotNull(actual);
        assertEquals(17, actual.size());
    }

    @Test
    @DisplayName("Get all masters by specific service")
    public void getAllMastersByService_Ok() throws Exception {
        //Given
        String category = "nails";
        //When
        MvcResult result = mockMvc.perform(
                        get("/masters/{category}", category)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        List<CommentDto> actual = objectMapper.readValue(
                result.getResponse().getContentAsString(), new TypeReference<>() {
                }
        );

        //Then
        assertNotNull(actual);
        assertEquals(5 , actual.size());
    }


    @Test
    @DisplayName("Find masters by not existing in category - expected result: "
            + "return exception - NOT FOUND")
    public void getAllMastersByService_NotOk() throws Exception {
        //Given
        String unknownCategory = "unknown";
        //Then
        mockMvc.perform(get("/masters/{category}", unknownCategory))
                .andExpect(status().isNotFound());
    }

    @Test
    @Sql(scripts = {
            "classpath:database/MasterControllerIntegrationTest/after/" +
                    "after_createComment_ValidRequestDto_Ok.sql",
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Create master - expected result: create master in DB, return created master")
    public void createComment_ValidRequestDto_Ok() throws Exception {
        //Given
        MasterRequestDto requestDto = createDefaultMaster();
        MasterDto expected = getResponseDto(requestDto);
        String jsonRequest = objectMapper.writeValueAsString(requestDto);
        //When
        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.post("/masters")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andReturn();

        MasterDto actual = objectMapper.readValue(result.getResponse()
                .getContentAsString(), MasterDto.class);
        //Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Create master with invalid FullName and Description" +
            " expected result: return exception")
    public void createMaster_InValidRequestDto_NotOk() throws Exception {
        //Given
        MasterRequestDto requestDto = createDefaultMaster();
        requestDto.setFullName(")))))");
        String jsonRequest = objectMapper.writeValueAsString(requestDto);
        //When
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/masters")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = {
            "classpath:database/MasterControllerIntegrationTest/before/" +
                    "before_deleteMaster_Ok.sql",
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @DisplayName("Delete master by id - expected result: delete master from DB")
    public void deleteMaster_Ok() throws Exception {
        mockMvc.perform(
                        delete("/masters/{id}", 18L))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Delete master by not existing in DB id - expected result: "
            + "return exception - NOT FOUND")
    public void deleteComment_InvalidId_NotOk() throws Exception {
        mockMvc.perform(
                        delete("/masters/{id}", 100L))
                .andExpect(status().isNotFound());
    }

    private static MasterDto getResponseDto(MasterRequestDto requestDto) {
        return new MasterDto()
                .setId(19L)
                .setFullName(requestDto.getFullName())
                .setCoverImage(requestDto.getCoverImage())
                .setQualification(requestDto.getQualification());

    }

    private static MasterRequestDto createDefaultMaster() {
        return new MasterRequestDto()
                .setFullName("Mykhailo")
                .setCoverImage("image")
                .setQualification(Qualification.HEADMASTER)
                .setServiceId(1L)
                .setPhoneNumber("(067) 303-3939");
    }
}
