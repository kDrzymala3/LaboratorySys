package com.pl.kd.Control;

import com.pl.kd.Model.Patient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class addPatientController {

    @GetMapping("/add")
    public String addPatientForm(Model model){
        model.addAttribute("patient", new Patient());
        return "addPatient";
    }

    @RequestMapping(value = "/addPatient", method = RequestMethod.POST)
    @ResponseBody
    public void addPatient(@ModelAttribute("patient") Patient patient){
        System.out.println("adding patient data"+ patient.getName());
    }
}
