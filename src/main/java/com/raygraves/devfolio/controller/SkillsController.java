package com.raygraves.devfolio.controller;

import com.raygraves.devfolio.model.Skills;
import com.raygraves.devfolio.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/skills")
public class SkillsController {

    private final SkillsService skillsService;

    @Autowired
    public SkillsController(SkillsService skillsService) {
        this.skillsService = skillsService;
    }

    @GetMapping
    public String listSkills(Model model) {
        List<Skills> skillsList = skillsService.getAllSkills();
        if (skillsList.isEmpty()) {
            skillsList = skillsService.createDefaultSkills();
        }
        model.addAttribute("skillsList", skillsList);
        return "skills/skills-list";
    }

    @GetMapping("/add")
    public String showSkillsForm(Model model) {
        model.addAttribute("skill", new Skills());
        return "skills/skills-form";
    }

    @PostMapping("/add")
    public String addSkill(@Valid @ModelAttribute Skills skill, BindingResult result) {
        if (result.hasErrors()) {
            return "skills/skills-form";
        }
        skillsService.createSkill(skill);
        return "redirect:/skills";
    }

    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Skills>> getAllSkills() {
        List<Skills> skillsList = skillsService.getAllSkills();
        return new ResponseEntity<>(skillsList, HttpStatus.OK);
    }

    @GetMapping("/paginated")
    @ResponseBody
    public ResponseEntity<Page<Skills>> getPaginatedSkills(Pageable pageable) {
        Page<Skills> skillsPage = skillsService.getSkillsPaginated(pageable);
        return new ResponseEntity<>(skillsPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Skills> getSkillById(@PathVariable Long id) {
        return skillsService.getSkillById(id)
                .map(skill -> new ResponseEntity<>(skill, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Skills> updateSkill(@PathVariable Long id, @Valid @RequestBody Skills skill) {
        Skills updatedSkill = skillsService.updateSkillById(id, skill);
        return new ResponseEntity<>(updatedSkill, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillsService.deleteSkillById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}