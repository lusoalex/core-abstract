package com.example.demo.dto.out;

import com.example.demo.dto.out.StockDto.StockDtoBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.math.BigInteger;
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

  public StockDto calculateState() {
    int totalQuantity = shoes.stream().map(StockShoeDto::getQuantity).reduce(BigInteger.ZERO, BigInteger::add).intValue();
    State state1;
    if (totalQuantity >= 30) {
      state1 = State.FULL;
    } else if (totalQuantity > 0) {
      state1 = State.SOME;
    } else {
      state1 = State.EMPTY;
    }
    return builder().shoes(shoes).state(state1).build();
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static class StockDtoBuilder {

  }


}
