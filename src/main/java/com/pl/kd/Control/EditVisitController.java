package com.pl.kd.Control;

import com.pl.kd.Model.Patient;
import com.pl.kd.Model.TestType;
import com.pl.kd.Model.Visit;
import com.pl.kd.repository.PatientRepository;
import com.pl.kd.repository.TestTypeRepository;
import com.pl.kd.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class EditVisitController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private TestTypeRepository testTypeRepository;


    @Autowired
    public EditVisitController(PatientRepository patientRepository, VisitRepository visitRepository){
        this.patientRepository=patientRepository;
        this.visitRepository=visitRepository;
    }

    @PostMapping("/editVisit/{patientId}")
    public String editVisit(@ModelAttribute("visit")Visit visit, @ModelAttribute("testTypeName")  String testTypeName, @PathVariable Integer patientId){
        Optional<Patient> patientById = patientRepository.findById(patientId);
        TestType testType = testTypeRepository.findByName(testTypeName);
        visit.setTestType(testType);
        patientById.ifPresent(patient1 -> visit.setPatient(patient1));
        visitRepository.save(visit);

        return patientById.map(patient1 -> "redirect:/patient/"+patient1.getId()).orElse("noPatient");
    }
}
