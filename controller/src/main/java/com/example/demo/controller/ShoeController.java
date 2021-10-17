package com.example.demo.controller;

import com.example.demo.dto.in.ShoeFilter;
import com.example.demo.dto.out.Shoes;
import com.example.demo.facade.ShoeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/shoes")
@RequiredArgsConstructor
public class ShoeController {

    private final ShoeFacade shoeFacade;

    @ResponseBody
    @GetMapping(path = "/search")
    public ResponseEntity<Shoes> all(ShoeFilter filter, @RequestHeader Integer version) {

        return ResponseEntity.ok(shoeFacade.get(version).search(filter));

    }

}
