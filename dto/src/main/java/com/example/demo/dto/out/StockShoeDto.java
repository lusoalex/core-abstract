package com.example.demo.dto.out;

import com.example.demo.dto.in.ShoeFilter.Color;
import com.example.demo.dto.out.StockShoeDto.StockShoeDtoBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
@JsonDeserialize(builder = StockShoeDtoBuilder.class)
public class StockShoeDto {

  BigInteger size;
  Color      color;
  BigInteger quantity;

  @JsonPOJOBuilder(withPrefix = "")
  public static class StockShoeDtoBuilder {

  }


}
