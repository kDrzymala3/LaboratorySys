package com.pl.kd.repository;

import com.pl.kd.Model.TestType;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface TestTypeRepository extends CrudRepository<TestType,Integer> {
    TestType findByName(String name);
    List<TestType> findAllByOrderByIdAsc();
    List<TestType> findAllByOrderByNameAsc();
    List<TestType> findAllByOrderByPriceAsc();
    List<TestType>findAllByNameContainingIgnoreCase(String name);
    List<TestType>findAllByPriceContaining(BigDecimal price);
}
