package com.raygraves.devfolio.controller;

import com.raygraves.devfolio.model.WorkHistory;
import com.raygraves.devfolio.service.WorkHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/work-history")
public class WorkHistoryController {

    private final WorkHistoryService workHistoryService;

    @Autowired
    public WorkHistoryController(WorkHistoryService workHistoryService) {
        this.workHistoryService = workHistoryService;
    }

    @PostMapping
    public ResponseEntity<WorkHistory> createWorkHistory(@Valid @RequestBody WorkHistory workHistory) {
        WorkHistory createdWorkHistory = workHistoryService.createWorkHistory(workHistory);
        return new ResponseEntity<>(createdWorkHistory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WorkHistory>> getAllWorkHistory() {
        List<WorkHistory> workHistoryList = workHistoryService.getAllWorkHistory();
        return new ResponseEntity<>(workHistoryList, HttpStatus.OK);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<WorkHistory>> getPaginatedWorkHistory(Pageable pageable) {
        Page<WorkHistory> workHistoryPage = workHistoryService.getWorkHistoryPaginated(pageable);
        return new ResponseEntity<>(workHistoryPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkHistory> getWorkHistoryById(@PathVariable Long id) {
        return workHistoryService.getWorkHistoryById(id)
                .map(workHistory -> new ResponseEntity<>(workHistory, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/contact-info/{contactInfoId}")
    public ResponseEntity<List<WorkHistory>> getWorkHistoryByContactInfoId(@PathVariable Long contactInfoId) {
        List<WorkHistory> workHistoryList = workHistoryService.getWorkHistoryByContactInfoId(contactInfoId);
        return new ResponseEntity<>(workHistoryList, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<WorkHistory>> searchWorkHistoryByCompany(@RequestParam String company) {
        List<WorkHistory> workHistoryList = workHistoryService.getWorkHistoryByCompanyContaining(company);
        return new ResponseEntity<>(workHistoryList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkHistory> updateWorkHistory(@PathVariable Long id, @Valid @RequestBody WorkHistory workHistory) {
        WorkHistory updatedWorkHistory = workHistoryService.updateWorkHistoryById(id, workHistory);
        return new ResponseEntity<>(updatedWorkHistory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkHistory(@PathVariable Long id) {
        workHistoryService.deleteWorkHistoryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/default")
    public ResponseEntity<List<WorkHistory>> createDefaultWorkHistory() {
        List<WorkHistory> defaultWorkHistory = workHistoryService.createDefaultWorkHistory();
        return new ResponseEntity<>(defaultWorkHistory, HttpStatus.CREATED);
    }
}