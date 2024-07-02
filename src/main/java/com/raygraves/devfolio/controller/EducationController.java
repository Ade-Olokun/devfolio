package com.raygraves.devfolio.controller;

import com.raygraves.devfolio.model.Education;
import com.raygraves.devfolio.service.EducationService;
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
@RequestMapping("/education")
public class EducationController {

    private final EducationService educationService;

    @Autowired
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping
    public String listEducation(Model model) {
        List<Education> educationList = educationService.getAllEducation();
        if (educationList.isEmpty()) {
            educationList = educationService.createDefaultEducation();
        }
        model.addAttribute("educationList", educationList);
        return "education/education-list";
    }

    @GetMapping("/add")
    public String showEducationForm(Model model) {
        model.addAttribute("education", new Education());
        return "education/education-form";
    }

    @PostMapping("/add")
    public String addEducation(@Valid @ModelAttribute Education education, BindingResult result) {
        if (result.hasErrors()) {
            return "education/education-form";
        }
        educationService.createEducation(education);
        return "redirect:/education";
    }

    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Education>> getAllEducation() {
        List<Education> educationList = educationService.getAllEducation();
        return new ResponseEntity<>(educationList, HttpStatus.OK);
    }

    @GetMapping("/paginated")
    @ResponseBody
    public ResponseEntity<Page<Education>> getPaginatedEducation(Pageable pageable) {
        Page<Education> educationPage = educationService.getEducationPaginated(pageable);
        return new ResponseEntity<>(educationPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Education> getEducationById(@PathVariable Long id) {
        return educationService.getEducationById(id)
                .map(education -> new ResponseEntity<>(education, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Education> updateEducation(@PathVariable Long id, @Valid @RequestBody Education education) {
        Education updatedEducation = educationService.updateEducationById(id, education);
        return new ResponseEntity<>(updatedEducation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id) {
        educationService.deleteEducationById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}