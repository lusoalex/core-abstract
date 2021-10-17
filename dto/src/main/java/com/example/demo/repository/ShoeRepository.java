package com.example.demo.repository;

import com.example.demo.dto.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShoeRepository extends JpaRepository<Shoe, UUID>  {

}
