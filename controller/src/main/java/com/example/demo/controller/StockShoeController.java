package com.example.demo.controller;

import com.example.demo.dto.StockShoe;
import com.example.demo.repository.StockShoeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/stock-shoe")
@RequiredArgsConstructor
public class StockShoeController {

    private final StockShoeRepository stockShoeRepository;

    @PatchMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public StockShoe updateShoesQuantityInStock(@RequestBody @Validated StockShoe stockShoe) {

        return stockShoeRepository.save(stockShoe);

    }

}
