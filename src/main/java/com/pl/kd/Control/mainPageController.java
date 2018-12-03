package com.pl.kd.Control;

import com.pl.kd.Model.Patient;
import com.pl.kd.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class mainPageController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    public mainPageController(PatientRepository patientRepository){this.patientRepository=patientRepository;}

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

    @GetMapping("/patient/{id}")
    public String patientInfo(@PathVariable Integer id,Model model){
        Optional<Patient> patientById = patientRepository.findById(id);
        patientById.ifPresent(patient -> model.addAttribute("Patient",patient));
        return patientById.map(patient -> "patientInfo").orElse("noPatient");
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id,Model model){
        patientRepository.deleteById(id);
        System.out.println("Deleting...");
        return "redirect:/";
    }
}
