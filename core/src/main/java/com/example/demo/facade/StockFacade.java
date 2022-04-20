package com.example.demo.facade;

import com.example.demo.core.IStockCore;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StockFacade {

  private final Map<Integer, IStockCore> implementations = new HashMap<>();

  public IStockCore get(Integer version){
    return implementations.get(version);
  }

  public void register(Integer version, IStockCore implementation){
    this.implementations.put(version, implementation);
  }

}
