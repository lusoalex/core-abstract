package com.example.demo.repository;

import com.example.demo.dto.StockShoe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StockShoeRepository extends JpaRepository<StockShoe, UUID> {

}
