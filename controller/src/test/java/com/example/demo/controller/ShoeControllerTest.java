package com.example.demo.controller;

import com.example.demo.MultipleCoreImplemSampleApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { MultipleCoreImplemSampleApplication.class })
public class ShoeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void shoeSearchV1Test() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/shoes/search")
                        .header("version", 1))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void shoeSearchV2Test() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/shoes/search")
                        .header("version", 2))
                .andExpect(status().isOk())
                .andDo(print());

    }

}
