package com.raygraves.devfolio.service;

import com.raygraves.devfolio.model.Skills;
import com.raygraves.devfolio.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SkillsService {

    private final SkillsRepository skillsRepository;

    @Autowired
    public SkillsService(SkillsRepository skillsRepository) {
        this.skillsRepository = skillsRepository;
    }

    // Create operations
    public Skills createSkill(Skills skill) {
        return skillsRepository.save(skill);
    }

    public List<Skills> createMultipleSkills(List<Skills> skillsList) {
        return skillsRepository.saveAll(skillsList);
    }

    // Read operations
    public List<Skills> getAllSkills() {
        return skillsRepository.findAll();
    }

    public Optional<Skills> getSkillById(Long id) {
        return skillsRepository.findById(id);
    }

    public Page<Skills> getSkillsPaginated(Pageable pageable) {
        return skillsRepository.findAll(pageable);
    }

    public List<Skills> getSkillsByContactInfoId(Long contactInfoId) {
        return skillsRepository.findByContactInfoId(contactInfoId);
    }

    public List<Skills> getSkillsBySkillNameContaining(String skillName) {
        return skillsRepository.findBySkillNameContaining(skillName);
    }

    // Update operations
    public Skills updateSkill(Skills skill) {
        return skillsRepository.save(skill);
    }

    public Skills updateSkillById(Long id, Skills updatedSkill) {
        return skillsRepository.findById(id)
                .map(existingSkill -> {
                    existingSkill.setSkillName(updatedSkill.getSkillName());
                    existingSkill.setDescription(updatedSkill.getDescription());
                    existingSkill.setProficiencyLevel(updatedSkill.getProficiencyLevel());
                    return skillsRepository.save(existingSkill);
                })
                .orElseGet(() -> {
                    updatedSkill.setId(id);
                    return skillsRepository.save(updatedSkill);
                });
    }

    // Delete operations
    public void deleteSkill(Skills skill) {
        skillsRepository.delete(skill);
    }

    public void deleteSkillById(Long id) {
        skillsRepository.deleteById(id);
    }

    public void deleteAllSkills() {
        skillsRepository.deleteAll();
    }

    // Utility methods
    public long countSkills() {
        return skillsRepository.count();
    }

    public boolean existsSkillById(Long id) {
        return skillsRepository.existsById(id);
    }

    // Method to create default data
    public List<Skills> createDefaultSkills() {
        List<Skills> defaultSkillsList = new ArrayList<>();

        defaultSkillsList.add(Skills.builder()
                .skillName("Java")
                .description("Proficient in Java programming language")
                .proficiencyLevel(8)
                .build());

        defaultSkillsList.add(Skills.builder()
                .skillName("Spring Framework")
                .description("Experienced with Spring Boot and Spring MVC")
                .proficiencyLevel(7)
                .build());

        defaultSkillsList.add(Skills.builder()
                .skillName("SQL")
                .description("Skilled in writing complex SQL queries")
                .proficiencyLevel(8)
                .build());

        return skillsRepository.saveAll(defaultSkillsList);
    }
}