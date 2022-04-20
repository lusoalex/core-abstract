package com.example.demo.dto.in;

import com.example.demo.dto.out.StockShoeDto;
import lombok.Data;

import java.util.List;

@Data
public class StockFilter {
    String name;
    List<StockShoeDto> shoes;
}
