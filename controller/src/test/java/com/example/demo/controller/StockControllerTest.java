package com.example.demo.controller;

import com.example.demo.MultipleCoreImplemSampleApplication;
import com.example.demo.dto.Shoe;
import com.example.demo.dto.Stock;
import com.example.demo.dto.StockShoe;
import com.example.demo.repository.ShoeRepository;
import com.example.demo.repository.StockRepository;
import com.example.demo.repository.StockShoeRepository;
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

import java.math.BigInteger;
import java.util.List;

import static com.example.demo.dto.in.ShoeFilter.Color.BLACK;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { MultipleCoreImplemSampleApplication.class })
public class StockControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ShoeRepository shoeRepository;

    @Autowired
    private StockShoeRepository stockShoeRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void getStockListTest() throws Exception {

        shoeRepository.save(Shoe.builder().name("nike1").color(BLACK).size(BigInteger.valueOf(40)).build());

        populateDatabaseStock ();

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/stock"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andDo(print());

    }

    @Test
    public void getStockByIdTest() throws Exception {

        shoeRepository.save(Shoe.builder().name("nike2").color(BLACK).size(BigInteger.valueOf(40)).build());

        List<Stock> stockList = populateDatabaseStock ();

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/stock/" + stockList.get(0).getId()))
                .andExpect(status().isOk())
                .andDo(print());

    }

    /**
     * @return populate database
     */
    private List<Stock> populateDatabaseStock () {

        stockRepository.save(Stock.builder().capacity(100).build());

        List<Shoe> shoeList = shoeRepository.findAll();
        List<Stock> stockList = stockRepository.findAll();

        stockShoeRepository.save(StockShoe.builder()
                .shoe(shoeList.get(0))
                .stock(stockList.get(0))
                .quantity(20)
                .build());

        return stockList;

    }

}
