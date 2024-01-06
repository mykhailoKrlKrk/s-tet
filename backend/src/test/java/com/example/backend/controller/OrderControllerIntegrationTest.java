package com.example.backend.controller;

import com.example.backend.dto.master.MasterDto;
import com.example.backend.dto.order.OrderRequestDto;
import com.example.backend.dto.order.OrderResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
public class OrderControllerIntegrationTest {
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
    @DisplayName("Get list of existing in DB orders - expected result: return list of orders")
    public void getAllOrders_Ok() throws Exception {
        //When
        MvcResult result = mockMvc.perform(
                        get("/orders"))
                .andExpect(status().isOk())
                .andReturn();
        List<MasterDto> actual = objectMapper.readValue(result
                .getResponse().getContentAsString(), new TypeReference<>() {
        });
        //Then
        assertNotNull(actual);
        assertEquals(0, actual.size());
    }

    @Test
    @Sql(scripts = {
            "classpath:database/OrderControllerIntegrationTest/after/" +
                    "after_createOrder_ValidRequestDto_Ok.sql",
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @DisplayName("Create order - expected result: create order in DB, return created order")
    public void createOrder_ValidRequestDto_Ok() throws Exception {
        //Given
        OrderRequestDto requestDto = createDefaultOrder();
        OrderResponseDto expected = getResponseDto(requestDto);
        String jsonRequest = objectMapper.writeValueAsString(requestDto);
        //When
        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.post("/orders")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andReturn();

        OrderResponseDto actual = objectMapper.readValue(result.getResponse()
                .getContentAsString(), OrderResponseDto.class);
        actual.setOrderDate(LocalDateTime.of(2024,6, 1, 16, 3, 3));

        //Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Create order with invalid FullName and Description" +
            " expected result: return exception")
    public void createOrder_InValidRequestDto_NotOk() throws Exception {
        //Given
        OrderRequestDto requestDto = createDefaultOrder();
        requestDto.setClientName("+++++");

        String jsonRequest = objectMapper.writeValueAsString(requestDto);
        //When
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/orders")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = {
            "classpath:database/OrderControllerIntegrationTest/before /" +
                    "before_before_deleteOrder_Ok.sql",
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @DisplayName("Delete order by id - expected result: delete order from DB")
    public void deleteOrder_Ok() throws Exception {
        mockMvc.perform(
                        delete("/orders/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Delete order by not existing in DB id - expected result: "
            + "return exception - NOT FOUND")
    public void deleteOrder_InvalidId_NotOk() throws Exception {
        mockMvc.perform(
                        delete("/orders/{id}", 100L))
                .andExpect(status().isNotFound());
    }

    private static OrderResponseDto getResponseDto(OrderRequestDto requestDto) {
        return new OrderResponseDto()
                .setId(2L)
                .setClientName(requestDto.getClientName())
                .setOrderDate(LocalDateTime.of(2024,6, 1, 16, 3, 3))
                .setOrderTotal(requestDto.getOrderTotal())
                .setComment(requestDto.getComment());

    }

    private static OrderRequestDto createDefaultOrder() {
        return new OrderRequestDto()
                .setOrderTotal(BigDecimal.valueOf(500))
                .setComment("some text")
                .setClientName("Joe")
                .setMasterId(1L)
                .setPhoneNumber("(049) 4848-848")
                .setServicesId(List.of(1L, 2L, 3L));
    }
}
