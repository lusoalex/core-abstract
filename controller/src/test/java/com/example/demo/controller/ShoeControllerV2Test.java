package com.example.demo.controller;

import com.example.demo.MultipleCoreImplemSampleApplication;
import com.example.demo.dto.Shoe;
import com.example.demo.dto.in.ShoeFilter;
import com.example.demo.repository.ShoeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigInteger;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { MultipleCoreImplemSampleApplication.class })
public class ShoeControllerV2Test {

    private MockMvc mockMvc;

    @Autowired
    private ShoeRepository shoeRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void shoeSearchTest() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/shoes-v2"))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void getShoesTest() throws Exception {

        Shoe shoe = Shoe.builder()
                .size(BigInteger.valueOf(35))
                .name("adidas")
                .color(ShoeFilter.Color.BLUE)
                .build();

        shoeRepository.save(shoe);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/shoes-v2")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andDo(print());

    }

    @Test
    public void addShoeTest() throws Exception {

        Shoe shoe = Shoe.builder()
                .size(BigInteger.valueOf(34))
                .name("adidas")
                .color(ShoeFilter.Color.BLUE)
                .build();

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/shoes-v2")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(shoe))
                )
                .andExpect(status().isCreated())
                .andDo(print());

    }

}
