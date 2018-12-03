package com.pl.kd.Control;

import com.pl.kd.Model.Patient;
import com.pl.kd.Model.TestType;
import com.pl.kd.Model.Visit;
import com.pl.kd.repository.PatientRepository;
import com.pl.kd.repository.TestTypeRepository;
import com.pl.kd.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class patientControler {


    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private TestTypeRepository testTypeRepository;


    @Autowired
    public patientControler(PatientRepository patientRepository, VisitRepository visitRepository){
        this.patientRepository=patientRepository;
        this.visitRepository=visitRepository;
    }

    @GetMapping("/patient/{id}")
    public String patientInfo(@PathVariable Integer id,Model model){
        Optional<Patient> patientById = patientRepository.findById(id);
        patientById.ifPresent(patient -> model.addAttribute("patient",patient));
        List<Visit> byPatientId = visitRepository.findByPatientId(id);
        model.addAttribute("visitList",byPatientId);
        Iterable<TestType> tests=testTypeRepository.findAll();
        model.addAttribute("testTypeList",tests);
        model.addAttribute("visit",new Visit());
        return patientById.map(patient -> "patientInfo").orElse("noPatient");
    }

    @GetMapping("/deleteVisit/{id}&{patientId}")
    public String deleteVisit(@PathVariable Integer id,@PathVariable Integer patientId, Model model){
        visitRepository.deleteById(id);
        System.out.println(patientId);
        Optional<Patient> byId = patientRepository.findById(patientId);
        return byId.map(patient1 -> "redirect:/patient/"+patientId).orElse("noPatient");
    }
    @PostMapping("/addVisit/{id}")
    public String addVisit(@ModelAttribute("visit") Visit visit, @PathVariable Integer id,Model model,@ModelAttribute("testTypeName") String testType){
        Optional<Patient> patientById = patientRepository.findById(id);
        patientById.ifPresent(patient -> model.addAttribute("patient",patient));
        patientById.ifPresent(patient -> visit.setPatient(patient));
        TestType testTypeObj = testTypeRepository.findByName(testType);
        visit.setTestType(testTypeObj);
        System.out.println(visit.toString());
        visitRepository.save(visit);

        List<Visit> byPatientId = visitRepository.findByPatientId(id);
        model.addAttribute("visitList",byPatientId);
        Iterable<TestType> tests=testTypeRepository.findAll();
        model.addAttribute("testTypeList",tests);
        model.addAttribute("visit",new Visit());
        return patientById.map(patient -> "redirect:/patient/"+patient.getId()).orElse("noPatient");
    }
}
