package com.example.demo.use_case;

import com.example.demo.MultipleCoreImplemSampleApplication;
import com.example.demo.dto.Shoe;
import com.example.demo.dto.Stock;
import com.example.demo.dto.StockShoe;
import com.example.demo.dto.in.ShoeFilter;
import com.example.demo.repository.ShoeRepository;
import com.example.demo.repository.StockRepository;
import com.example.demo.repository.StockShoeRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { MultipleCoreImplemSampleApplication.class })
public class StockShoeTest {

    private MockMvc mockMvc;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ShoeRepository shoeRepository;

    @Autowired
    private StockShoeRepository stockShoeRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void getShoesTest() throws Exception {

        Stock stock = createStock(40);
        Shoe shoe1 = createShoe("adidas", ShoeFilter.Color.BLUE, 40);
        Shoe shoe2 = createShoe("adidas", ShoeFilter.Color.BLUE, 41);
        Shoe shoe3 = createShoe("nike", ShoeFilter.Color.BLACK, 32);
        Shoe shoe4 = createShoe("nike", ShoeFilter.Color.BLACK, 35);

        updateShoesQuantityInStock(stock, shoe1, 10);
        updateShoesQuantityInStock(stock, shoe2, 3);
        updateShoesQuantityInStock(stock, shoe3, 3);
        updateShoesQuantityInStock(stock, shoe4, 5);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/stock/" + stock.getId()))
                .andExpect(status().isOk())
                .andDo(print());

    }

    /**
     * @param stock the stock to update
     * @param shoe the shoes that needs to be updated in the stock
     * @param quantity the quantity of the shoes model in the stock
     */
    private void updateShoesQuantityInStock(Stock stock, Shoe shoe, int quantity) throws Exception {

        StockShoe stockShoe = StockShoe.builder()
                .stock(stock)
                .shoe(shoe)
                .quantity(quantity)
                .build();

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .patch("/stock-shoe")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stockShoe))
                )
                .andExpect(status().isCreated())
                .andDo(print());

    }

    /**
     * @param capacity the stock capacity
     * @return the created stock
     */
    private Stock createStock(int capacity) {

        Stock stock = Stock.builder()
                .capacity(capacity)
                .build();

        return stockRepository.save(stock);

    }

    /**
     *
     * @param name the new shoes model name
     * @param color the new color associated to the new shoes model and size
     * @param size the new size associated to the new shoes model and color
     * @return the created shoes model
     */
    private Shoe createShoe(String name, ShoeFilter.Color color, int size) {

        Shoe shoe = Shoe.builder()
                .name(name)
                .color(color)
                .size(BigInteger.valueOf(size))
                .build();

        return shoeRepository.save(shoe);

    }

}
