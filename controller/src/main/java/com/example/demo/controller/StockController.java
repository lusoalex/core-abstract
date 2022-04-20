package com.example.demo.controller;

import com.example.demo.dto.in.StockFilter;
import com.example.demo.dto.out.StockDto;
import com.example.demo.exceptions.GeneralException;
import com.example.demo.facade.StockFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/stock")
@RequiredArgsConstructor
public class StockController {

  private final StockFacade stockFacade;

  @GetMapping
  public ResponseEntity<StockDto> all(@Valid @RequestBody StockFilter filter, @RequestHeader Integer version){
    return ResponseEntity.ok(stockFacade.get(version).getAll(filter.getName()));
  }

  @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<StockDto> update(@Valid @RequestBody StockFilter filter, @RequestHeader Integer version) throws GeneralException{
    return ResponseEntity.ok(stockFacade.get(version).updateStock(filter.getName(), filter.getShoes()));
  }

}
