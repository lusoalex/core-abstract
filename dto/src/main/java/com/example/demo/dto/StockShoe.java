package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// cannot associate the same shoe to the same stock more than once, only the quantity needs to be updated
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UniqueNameAndSizeAndColor",
                columnNames = {"stock_id", "shoe_id"})
})
public class StockShoe implements Serializable {

    @Id
    @JsonIgnore
    @GeneratedValue
    private UUID id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "shoe_id")
    private Shoe shoe;

    private Integer quantity;

}
