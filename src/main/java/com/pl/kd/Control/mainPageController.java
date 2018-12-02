package com.pl.kd.Control;

import com.pl.kd.Model.Patient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class mainPageController {

    @GetMapping("/")
    public String init(Model model){
        model.addAttribute("patient", new Patient());
        return "index";
    }

    @PostMapping("/addPatient")
    public String addPatient(@ModelAttribute("patient") Patient patient ){
        System.out.println("adding patient data"+ patient.getName());
        return "index";
    }
}
