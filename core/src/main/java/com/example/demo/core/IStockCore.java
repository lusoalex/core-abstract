package com.example.demo.core;

import com.example.demo.dto.out.StockDto;
import com.example.demo.dto.out.StockShoeDto;
import com.example.demo.exceptions.GeneralException;

import java.util.List;

public interface IStockCore {
  StockDto getAll(String name);
  StockDto updateStock(String name, List<StockShoeDto> shoes) throws GeneralException;
}
