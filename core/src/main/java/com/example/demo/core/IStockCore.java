package com.example.demo.core;

import com.example.demo.dto.out.StockDto;
import com.example.demo.dto.out.StockShoeDto;

import java.util.List;

public interface IStockCore {
  StockDto getAll();
  StockDto updateStock(List<StockShoeDto> shoes) throws Exception;
}
