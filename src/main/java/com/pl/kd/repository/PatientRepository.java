package com.pl.kd.repository;

import com.pl.kd.Model.Patient;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface PatientRepository extends CrudRepository<Patient,Integer> {
    Optional<Patient> findById(Integer id);

}