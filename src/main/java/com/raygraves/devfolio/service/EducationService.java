package com.raygraves.devfolio.service;

import com.raygraves.devfolio.model.Education;
import com.raygraves.devfolio.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EducationService {

    private final EducationRepository educationRepository;

    @Autowired
    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    // Create operations
    public Education createEducation(Education education) {
        return educationRepository.save(education);
    }

    public List<Education> createMultipleEducation(List<Education> educationList) {
        return educationRepository.saveAll(educationList);
    }

    // Read operations
    public List<Education> getAllEducation() {
        return educationRepository.findAll();
    }

    public Optional<Education> getEducationById(Long id) {
        return educationRepository.findById(id);
    }

    public Page<Education> getEducationPaginated(Pageable pageable) {
        return educationRepository.findAll(pageable);
    }

    public List<Education> getEducationByContactInfoId(Long contactInfoId) {
        return educationRepository.findByContactInfoId(contactInfoId);
    }

    public List<Education> getEducationByInstitutionContaining(String institution) {
        return educationRepository.findByInstitutionContaining(institution);
    }

    // Update operations
    public Education updateEducation(Education education) {
        return educationRepository.save(education);
    }

    public Education updateEducationById(Long id, Education updatedEducation) {
        return educationRepository.findById(id)
                .map(existingEducation -> {
                    existingEducation.setInstitution(updatedEducation.getInstitution());
                    existingEducation.setDegree(updatedEducation.getDegree());
                    existingEducation.setFieldOfStudy(updatedEducation.getFieldOfStudy());
                    existingEducation.setStartDate(updatedEducation.getStartDate());
                    existingEducation.setEndDate(updatedEducation.getEndDate());
                    return educationRepository.save(existingEducation);
                })
                .orElseGet(() -> {
                    updatedEducation.setId(id);
                    return educationRepository.save(updatedEducation);
                });
    }

    // Delete operations
    public void deleteEducation(Education education) {
        educationRepository.delete(education);
    }

    public void deleteEducationById(Long id) {
        educationRepository.deleteById(id);
    }

    public void deleteAllEducation() {
        educationRepository.deleteAll();
    }

    // Utility methods
    public long countEducation() {
        return educationRepository.count();
    }

    public boolean existsEducationById(Long id) {
        return educationRepository.existsById(id);
    }

    // Method to create default data
    public List<Education> createDefaultEducation() {
        List<Education> defaultEducationList = new ArrayList<>();

        defaultEducationList.add(Education.builder()
                .institution("University of Example")
                .degree("Bachelor of Science")
                .fieldOfStudy("Computer Science")
                .startDate(LocalDateTime.of(2015, 9, 1, 0, 0))
                .endDate(LocalDateTime.of(2019, 6, 30, 0, 0))
                .build());

        defaultEducationList.add(Education.builder()
                .institution("Tech Institute")
                .degree("Master of Science")
                .fieldOfStudy("Software Engineering")
                .startDate(LocalDateTime.of(2019, 9, 1, 0, 0))
                .endDate(LocalDateTime.of(2021, 6, 30, 0, 0))
                .build());

        return educationRepository.saveAll(defaultEducationList);
    }
}