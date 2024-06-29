package com.raygraves.devfolio.service;

import com.raygraves.devfolio.model.WorkHistory;
import com.raygraves.devfolio.repository.WorkHistoryRepository;
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
public class WorkHistoryService {

    private final WorkHistoryRepository workHistoryRepository;

    @Autowired
    public WorkHistoryService(WorkHistoryRepository workHistoryRepository) {
        this.workHistoryRepository = workHistoryRepository;
    }

    // Create operations
    public WorkHistory createWorkHistory(WorkHistory workHistory) {
        return workHistoryRepository.save(workHistory);
    }

    public List<WorkHistory> createMultipleWorkHistory(List<WorkHistory> workHistoryList) {
        return workHistoryRepository.saveAll(workHistoryList);
    }

    // Read operations
    public List<WorkHistory> getAllWorkHistory() {
        return workHistoryRepository.findAll();
    }

    public Optional<WorkHistory> getWorkHistoryById(Long id) {
        return workHistoryRepository.findById(id);
    }

    public Page<WorkHistory> getWorkHistoryPaginated(Pageable pageable) {
        return workHistoryRepository.findAll(pageable);
    }

    public List<WorkHistory> getWorkHistoryByContactInfoId(Long contactInfoId) {
        return workHistoryRepository.findByContactInfoId(contactInfoId);
    }

    public List<WorkHistory> getWorkHistoryByCompanyContaining(String company) {
        return workHistoryRepository.findByCompanyContaining(company);
    }

    // Update operations
    public WorkHistory updateWorkHistory(WorkHistory workHistory) {
        return workHistoryRepository.save(workHistory);
    }

    public WorkHistory updateWorkHistoryById(Long id, WorkHistory updatedWorkHistory) {
        return workHistoryRepository.findById(id)
                .map(existingWorkHistory -> {
                    existingWorkHistory.setCompany(updatedWorkHistory.getCompany());
                    existingWorkHistory.setPosition(updatedWorkHistory.getPosition());
                    existingWorkHistory.setDescription(updatedWorkHistory.getDescription());
                    existingWorkHistory.setStartDate(updatedWorkHistory.getStartDate());
                    existingWorkHistory.setEndDate(updatedWorkHistory.getEndDate());
                    return workHistoryRepository.save(existingWorkHistory);
                })
                .orElseGet(() -> {
                    updatedWorkHistory.setId(id);
                    return workHistoryRepository.save(updatedWorkHistory);
                });
    }

    // Delete operations
    public void deleteWorkHistory(WorkHistory workHistory) {
        workHistoryRepository.delete(workHistory);
    }

    public void deleteWorkHistoryById(Long id) {
        workHistoryRepository.deleteById(id);
    }
    public List<WorkHistory> createDefaultWorkHistory() {
        List<WorkHistory> defaultWorkHistoryList = new ArrayList<>();

        defaultWorkHistoryList.add(WorkHistory.builder()
                .company("Tech Innovations Inc.")
                .position("Software Developer")
                .description("Developed and maintained web applications using Java and Spring Framework")
                .startDate(LocalDateTime.of(2018, 6, 1, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 0, 0))
                .build());

        defaultWorkHistoryList.add(WorkHistory.builder()
                .company("Data Systems LLC")
                .position("Senior Java Developer")
                .description("Led a team of developers in creating scalable microservices")
                .startDate(LocalDateTime.of(2021, 1, 1, 0, 0))
                .endDate(null) // Assuming this is the current job
                .build());

        return workHistoryRepository.saveAll(defaultWorkHistoryList);
    }
}