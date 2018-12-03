package com.pl.kd.Control;

import com.pl.kd.Model.Patient;
import com.pl.kd.repository.PatientRepository;
import com.pl.kd.repository.TestTypeRepository;
import com.pl.kd.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        model.addAttribute("patientList",patients);
        model.addAttribute("patient", new Patient());
        return "index";
    }

    @PostMapping("/addPatient")
    public String addPatient(@ModelAttribute("patient") Patient patient ){
        patientRepository.save(patient);
        return "redirect:/";
    }

    @GetMapping("/sortPatientById")
    public String sortById(Model model){
        List<Patient> patientList = patientRepository.findAllByOrderByIdAsc();
        model.addAttribute("patientList",patientList);
        model.addAttribute("patient",new Patient());
        return "index";
    }

    @GetMapping("/sortPatientByName")
    public String sortByName(Model model){
        List<Patient> patientsList = patientRepository.findAllByOrderByNameAsc();
        model.addAttribute("patientList",patientsList);
        model.addAttribute("patient",new Patient());
        return "index";
    }

    @GetMapping("/sortPatientBySurname")
    public String sortBySurname(Model model){
        List<Patient> patientsList = patientRepository.findAllByOrderBySurnameAsc();
        model.addAttribute("patientList",patientsList);
        model.addAttribute("patient",new Patient());
        return "index";
    }




    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id,Model model){
        try {
            patientRepository.deleteById(id);
        }catch (Exception e){
            System.out.println("uppps error");
            return "redirect:/";
        }

        return "redirect:/";
    }
}
