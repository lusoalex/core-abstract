package com.example.demo.controller;

import com.example.demo.dto.Stock;
import com.example.demo.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockRepository stockRepository;

    @GetMapping
    @ResponseBody
    public List<Stock> all() {

        return stockRepository.findAll();

    }

    @ResponseBody
    @GetMapping("/{id}")
    public Stock get(@PathVariable UUID id) {

        return stockRepository.findById(id).orElse(null);

    }

}
