package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("skills")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("")
    public String displayAllSkills (Model model, SkillRepository skillRepository) {
        model.addAttribute("skills", skillRepository.findAll());
        return "skills/index";
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

        Optional<Skill> optSkill = skillRepository.findById(skillId);
        if (optSkill.isPresent()) {
            SkillRepository skillRepository = (SkillRepository) optSkill.get();
            model.addAttribute("skillId", skillId);
            return "skills/view";
        } else {
            return "redirect:../";
        }

    }
}
