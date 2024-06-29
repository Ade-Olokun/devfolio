package com.raygraves.devfolio.controller;

import com.raygraves.devfolio.model.Education;
import com.raygraves.devfolio.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/education")
public class EducationController {

    private final EducationService educationService;

    @Autowired
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PostMapping
    public ResponseEntity<Education> createEducation(@Valid @RequestBody Education education) {
        Education createdEducation = educationService.createEducation(education);
        return new ResponseEntity<>(createdEducation, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Education>> getAllEducation() {
        List<Education> educationList = educationService.getAllEducation();
        return new ResponseEntity<>(educationList, HttpStatus.OK);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Education>> getPaginatedEducation(Pageable pageable) {
        Page<Education> educationPage = educationService.getEducationPaginated(pageable);
        return new ResponseEntity<>(educationPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Education> getEducationById(@PathVariable Long id) {
        return educationService.getEducationById(id)
                .map(education -> new ResponseEntity<>(education, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/contact-info/{contactInfoId}")
    public ResponseEntity<List<Education>> getEducationByContactInfoId(@PathVariable Long contactInfoId) {
        List<Education> educationList = educationService.getEducationByContactInfoId(contactInfoId);
        return new ResponseEntity<>(educationList, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Education>> searchEducationByInstitution(@RequestParam String institution) {
        List<Education> educationList = educationService.getEducationByInstitutionContaining(institution);
        return new ResponseEntity<>(educationList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Education> updateEducation(@PathVariable Long id, @Valid @RequestBody Education education) {
        Education updatedEducation = educationService.updateEducationById(id, education);
        return new ResponseEntity<>(updatedEducation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long id) {
        educationService.deleteEducationById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/default")
    public ResponseEntity<List<Education>> createDefaultEducation() {
        List<Education> defaultEducation = educationService.createDefaultEducation();
        return new ResponseEntity<>(defaultEducation, HttpStatus.CREATED);
    }
}