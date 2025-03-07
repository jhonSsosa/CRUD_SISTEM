package com.example.accessingdatajpa.repository;

import com.example.accessingdatajpa.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByAddress(String address);
}
