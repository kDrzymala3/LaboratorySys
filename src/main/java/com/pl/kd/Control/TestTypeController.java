package com.pl.kd.Control;

import com.pl.kd.Model.Patient;
import com.pl.kd.Model.TestType;
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

@Controller
public class TestTypeController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private TestTypeRepository testTypeRepository;


    @Autowired
    public TestTypeController(PatientRepository patientRepository, VisitRepository visitRepository,TestTypeRepository testTypeRepository){
        this.patientRepository=patientRepository;
        this.visitRepository=visitRepository;
        this.testTypeRepository=testTypeRepository;
    }


    @GetMapping("/testRange")
    public String testRange(Model model){
        Iterable<TestType> testTypes = testTypeRepository.findAll();
        model.addAttribute("testsList",testTypes);
        model.addAttribute("testType",new TestType());
        return "/testRange";
    }

    @PostMapping("/addTest")
    public String addTest(@ModelAttribute("testType")TestType testType){
        testTypeRepository.save(testType);
        return "redirect:testRange";
    }

    @GetMapping("/deleteTest/{id}")
    public String deleteTest(@PathVariable Integer id, Model model){
        try {
            testTypeRepository.deleteById(id);
        }catch (Exception e){
            System.out.println("Cannot delete");
        }
        model.addAttribute("testType",new TestType());
        Iterable<TestType> testTypes = testTypeRepository.findAll();
        model.addAttribute("testsList",testTypes);
        return "testRange";
    }

    @GetMapping("/sortTestById")
    public String sortById(Model model){
        List<TestType> testsList = testTypeRepository.findAllByOrderByIdAsc();
        model.addAttribute("testsList",testsList);
        model.addAttribute("testType",new TestType());
        return "testRange";
    }

    @GetMapping("/sortTestByName")
    public String sortByName(Model model){
        List<TestType> testsList = testTypeRepository.findAllByOrderByNameAsc();
        model.addAttribute("testsList",testsList);
        model.addAttribute("testType",new TestType());
        return "testRange";
    }

    @GetMapping("/sortTestByPrice")
    public String sortBySurname(Model model){
        List<TestType> testsList = testTypeRepository.findAllByOrderByPriceAsc();
        model.addAttribute("testsList",testsList);
        model.addAttribute("testType",new TestType());
        return "testRange";
    }
}
