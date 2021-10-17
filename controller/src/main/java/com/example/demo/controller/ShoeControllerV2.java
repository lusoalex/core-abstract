package com.example.demo.controller;

import com.example.demo.dto.Shoe;
import com.example.demo.repository.ShoeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/shoes-v2")
@RequiredArgsConstructor
public class ShoeControllerV2 {

    private final ShoeRepository shoeRepository;

    @GetMapping
    @ResponseBody
    public List<Shoe> all() {

        return shoeRepository.findAll();

    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Shoe addShoe(@RequestBody @Validated Shoe shoe) {

        return shoeRepository.save(shoe);

    }

}
