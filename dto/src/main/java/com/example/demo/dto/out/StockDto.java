package com.example.demo.dto.out;

import com.example.demo.dto.out.StockDto.StockDtoBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@JsonDeserialize(builder = StockDtoBuilder.class)
public class StockDto {


  State state;
  List<StockShoeDto> shoes;

  public enum State{

    FULL,
    EMPTY,
    SOME,
    ;

  }

  @JsonPOJOBuilder(withPrefix = "")
  public static class StockDtoBuilder {

  }


}
