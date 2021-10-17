package com.example.demo.serializer;

import com.example.demo.dto.StockShoe;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StockSetSerializer extends JsonSerializer<Set<StockShoe>> {

    @Override
    public void serialize(Set<StockShoe> stockShoes, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        Set<StockShoe> stockShoeSet = new HashSet<>();

        for (StockShoe stockShoe : stockShoes) {

            stockShoeSet.add(StockShoe.builder()
                    .quantity(stockShoe.getQuantity())
                    .shoe(stockShoe.getShoe())
                    .build());

        }

        jsonGenerator.writeObject(stockShoeSet);

    }

}
