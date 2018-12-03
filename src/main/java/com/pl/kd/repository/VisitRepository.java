package com.pl.kd.repository;

import com.pl.kd.Model.Visit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VisitRepository extends CrudRepository<Visit,Integer> {
    List<Visit> findByPatientId(Integer id);


}
