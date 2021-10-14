package com.example.demo.dto;

import com.example.demo.dto.in.ShoeFilter.Color;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shoe {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigInteger size;

    @Column(nullable = false)
    private Color color;

    @JsonIgnore
    @OneToMany(mappedBy = "shoe")
    private Set<StockShoe> stockShoeSet;

}
