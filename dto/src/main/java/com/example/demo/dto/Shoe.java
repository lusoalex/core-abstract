package com.example.demo.dto;

import com.example.demo.dto.in.ShoeFilter.Color;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UniqueNameAndSizeAndColor",
                columnNames = {"name", "size", "color"})
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Shoe {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    @NotNull(message = "Name cannot be null")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Size cannot be null")
    private BigInteger size;

    @Column(nullable = false)
    @NotNull(message = "Color cannot be null")
    private Color color;

    @JsonIgnore
    @OneToMany(mappedBy = "shoe")
    private Set<StockShoe> stockShoeSet;

}
