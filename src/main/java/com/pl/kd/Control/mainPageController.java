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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class mainPageController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private TestTypeRepository testTypeRepository;

    @Autowired
    public mainPageController(PatientRepository patientRepository, VisitRepository visitRepository, TestTypeRepository testTypeRepository){
        this.patientRepository=patientRepository;
        this.visitRepository=visitRepository;
        this.testTypeRepository=testTypeRepository;
    }

    @GetMapping("/")
    public String init(Model model){
        Iterable<Patient> patients = patientRepository.findAll();
        for (Patient p:patients){
            System.out.println(p.toString());
        }
        model.addAttribute("patientLIst",patients);
        model.addAttribute("patient", new Patient());
        return "index";
    }

    @PostMapping("/addPatient")
    public String addPatient(@ModelAttribute("patient") Patient patient ){
        patientRepository.save(patient);
        return "redirect:/";
    }



    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id,Model model){
        patientRepository.deleteById(id);
        System.out.println("Deleting...");
        return "redirect:/";
    }
}
