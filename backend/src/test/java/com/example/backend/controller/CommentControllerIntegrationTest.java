package com.example.backend.controller;

import com.example.backend.dto.comment.CommentDto;
import com.example.backend.dto.comment.CommentRequestDto;
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
public class CommentControllerIntegrationTest {

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
    @DisplayName("Get list of existing in DB comments - expected result: return list of comments")
    public void getAllComments_ReturnListOfExistingComments() throws Exception {
        //When
        MvcResult result = mockMvc.perform(
                        get("/comments"))
                .andExpect(status().isOk())
                .andReturn();
        List<CommentDto> actual = objectMapper.readValue(result
                .getResponse().getContentAsString(), new TypeReference<>() {
        });
        //Then
        assertNotNull(actual);
        assertEquals(12, actual.size());
    }

    @Test
    @DisplayName("Get all comments by specific service")
    public void getAllCommentsByService_Ok() throws Exception {
        //Given
        String category = "nails";
        //When
        MvcResult result = mockMvc.perform(
                        get("/comments/{category}", category)
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
        assertEquals(3, actual.size());
    }


    @Test
    @DisplayName("Find comments by not existing in category - expected result: "
            + "return exception - NOT FOUND")
    public void getAllCommentsByService_NotOk() throws Exception {
        //Given
        String unknownCategory = "unknown";
        //Then
        mockMvc.perform(get("/comments/{category}", unknownCategory))
                .andExpect(status().isNotFound());
    }

    @Test
    @Sql(scripts = {
            "classpath:database/CommentControllerIntegrationTest/after/" +
                    "after_createComment_ValidRequestDto_Ok.sql",
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @DisplayName("Create comment - expected result: create comment in DB, return created comment")
    public void createComment_ValidRequestDto_Ok() throws Exception {
        //Given
        String category = "nails";
        CommentRequestDto requestDto = createDefaultComment();
        CommentDto expected = getResponseDto(requestDto);
        String jsonRequest = objectMapper.writeValueAsString(requestDto);
        //When
        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.post("/comments/{category}", category)
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andReturn();

        CommentDto actual = objectMapper.readValue(result.getResponse()
                .getContentAsString(), CommentDto.class);
        //Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Create comment with invalid FullName and Description" +
            " expected result: return exception")
    public void createComment_InValidRequestDto_NotOk() throws Exception {
        //Given
        String category = "nails";
        CommentRequestDto requestDto = createDefaultComment();
        requestDto.setFullName("");
        requestDto.setDescription("!!)!)!");
        CommentDto expected = getResponseDto(requestDto);
        String jsonRequest = objectMapper.writeValueAsString(requestDto);
        //When
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/comments/{category}", category)
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = {
            "classpath:database/CommentControllerIntegrationTest/before/" +
                    "before_deleteComment_Ok.sql",
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @DisplayName("Delete comment by id - expected result: delete comment from DB")
    public void deleteComment_Ok() throws Exception {
        mockMvc.perform(
                        delete("/comments/{id}", 13L))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Delete comment by not existing in DB id - expected result: "
            + "return exception - NOT FOUND")
    public void deleteComment_InvalidId_NotOk() throws Exception {
        mockMvc.perform(
                        delete("/comments/{id}", 100L))
                .andExpect(status().isNotFound());
    }

    private static CommentDto getResponseDto(CommentRequestDto requestDto) {
        return new CommentDto()
                .setId(14L)
                .setFullName(requestDto.getFullName())
                .setDescription(requestDto.getDescription());

    }

    private static CommentRequestDto createDefaultComment() {
        return new CommentRequestDto()
                .setFullName("Mykhailo")
                .setDescription("here is my comment)");
    }
}
