package com.example.demo.controller;

import com.example.demo.dto.Stock;
import com.example.demo.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockRepository stockRepository;

    @GetMapping
    public List<Stock> all() {

        return stockRepository.findAll();

    }

    @GetMapping("/{id}")
    public Stock get(UUID id) {

        return stockRepository.findById(id).orElse(null);

    }

}
