package com.pl.kd.repository;

import com.pl.kd.Model.TestType;
import org.springframework.data.repository.CrudRepository;

public interface TestTypeRepository extends CrudRepository<TestType,Integer> {
    TestType findByName(String name);
}
