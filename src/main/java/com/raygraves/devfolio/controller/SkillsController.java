package com.raygraves.devfolio.controller;

import com.raygraves.devfolio.model.Skills;
import com.raygraves.devfolio.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillsController {

    private final SkillsService skillsService;

    @Autowired
    public SkillsController(SkillsService skillsService) {
        this.skillsService = skillsService;
    }

    @PostMapping
    public ResponseEntity<Skills> createSkill(@Valid @RequestBody Skills skill) {
        Skills createdSkill = skillsService.createSkill(skill);
        return new ResponseEntity<>(createdSkill, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Skills>> getAllSkills() {
        List<Skills> skillsList = skillsService.getAllSkills();
        return new ResponseEntity<>(skillsList, HttpStatus.OK);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Skills>> getPaginatedSkills(Pageable pageable) {
        Page<Skills> skillsPage = skillsService.getSkillsPaginated(pageable);
        return new ResponseEntity<>(skillsPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skills> getSkillById(@PathVariable Long id) {
        return skillsService.getSkillById(id)
                .map(skill -> new ResponseEntity<>(skill, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/contact-info/{contactInfoId}")
    public ResponseEntity<List<Skills>> getSkillsByContactInfoId(@PathVariable Long contactInfoId) {
        List<Skills> skillsList = skillsService.getSkillsByContactInfoId(contactInfoId);
        return new ResponseEntity<>(skillsList, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Skills>> searchSkillsByName(@RequestParam String name) {
        List<Skills> skillsList = skillsService.getSkillsBySkillNameContaining(name);
        return new ResponseEntity<>(skillsList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Skills> updateSkill(@PathVariable Long id, @Valid @RequestBody Skills skill) {
        Skills updatedSkill = skillsService.updateSkillById(id, skill);
        return new ResponseEntity<>(updatedSkill, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillsService.deleteSkillById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/default")
    public ResponseEntity<List<Skills>> createDefaultSkills() {
        List<Skills> defaultSkills = skillsService.createDefaultSkills();
        return new ResponseEntity<>(defaultSkills, HttpStatus.CREATED);
    }
}