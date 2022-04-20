package com.example.demo.controller;

import com.example.demo.dto.out.StockDto;
import com.example.demo.dto.out.StockShoeDto;
import com.example.demo.facade.StockFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/stock")
@RequiredArgsConstructor
public class StockController {

  private final StockFacade stockFacade;

  @GetMapping(path = "/")
  public ResponseEntity<StockDto> all(@RequestHeader Integer version){
    return ResponseEntity.ok(stockFacade.get(version).getAll());
  }

  @PatchMapping(path = "/")
  public ResponseEntity<StockDto> update(List<StockShoeDto> shoes, @RequestHeader Integer version){
    try {
      return ResponseEntity.ok(stockFacade.get(version).updateStock(shoes));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


}
