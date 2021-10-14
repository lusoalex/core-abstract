package com.example.demo.dto;

import com.example.demo.exception.InvalidStockStateException;
import com.example.demo.serializer.StockSetSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @GeneratedValue
    private UUID id;

    @JsonIgnore
    @Column(nullable = false)
    private Integer capacity;

    @JsonProperty("shoes")
    @OneToMany(mappedBy = "stock")
    @JsonSerialize(using = StockSetSerializer.class)
    private Set<StockShoe> stockShoeSet;

    /**
     * @return the current stock state
     */
    @JsonProperty("state")
    public StockStateEnum getState () {

        int quantity = stockShoeSet.stream().mapToInt(StockShoe::getQuantity).sum();

        if (quantity == 0) {
            return StockStateEnum.EMPTY;
        } else if (quantity == capacity) {
            return StockStateEnum.FULL;
        } else if (quantity < capacity && quantity > 0) {
            return StockStateEnum.SOME;
        } else {
            throw new InvalidStockStateException();
        }

    }

}
