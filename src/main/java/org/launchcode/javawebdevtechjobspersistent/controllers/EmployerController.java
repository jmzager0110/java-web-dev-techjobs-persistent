package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping("")
    public String index (Model model) {
        model.addAttribute("employers", employerRepository.findAll());

        return "employers/index";
    }


    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmp, Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "employers/add";
        } else {
           employerRepository.save(newEmp);
            return "redirect:";
        }
    }

    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {
        Optional <Employer> optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) {
            EmployerRepository employerRepository = (EmployerRepository) optEmployer.get();
            model.addAttribute("employerId", employerId);
            return "employers/view";
        } else {
            return "redirect:../";
        }
    }
}
