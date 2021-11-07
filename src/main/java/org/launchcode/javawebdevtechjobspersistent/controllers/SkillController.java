package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    @PostMapping("index")
    public String displayAllSkills (Model model, SkillRepository skillRepository) {
        model.addAttribute("skills", skillRepository.findAll());
        return "/skills";
    }

    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
        model.addAttribute(new Skill());
        return "skills/add";
    }
    @PostMapping("add")
    public String processAddSkillForm (@ModelAttribute @Valid SkillRepository skillRepository, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "skills/add";
        }
        model.addAttribute("skills", skillRepository.findAll());
        return "redirect:";

    }

    @GetMapping("view/{skillId}")
    public String displayViewSkill (Model model,  @PathVariable int skillId) {

        Optional<SkillRepository> optSkill = skillRepository.findById(skillId);
        if (optSkill.isPresent()) {
            SkillRepository skillRepository = (SkillRepository) optSkill.get();
            model.addAttribute("skillId", skillId);
            return "skills/view";
        } else {
            return "redirect:../";
        }

    }
}
