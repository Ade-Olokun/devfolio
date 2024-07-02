package com.raygraves.devfolio.controller;

import com.raygraves.devfolio.model.WorkHistory;
import com.raygraves.devfolio.service.WorkHistoryService;
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
@RequestMapping("/work-history")
public class WorkHistoryController {

    private final WorkHistoryService workHistoryService;

    @Autowired
    public WorkHistoryController(WorkHistoryService workHistoryService) {
        this.workHistoryService = workHistoryService;
    }

    @GetMapping
    public String listWorkHistory(Model model) {
        List<WorkHistory> workHistoryList = workHistoryService.getAllWorkHistory();
        if (workHistoryList.isEmpty()) {
            workHistoryList = workHistoryService.createDefaultWorkHistory();
        }
        model.addAttribute("workHistoryList", workHistoryList);
        return "work-history/work-history-list";
    }

    @GetMapping("/add")
    public String showWorkHistoryForm(Model model) {
        model.addAttribute("workHistory", new WorkHistory());
        return "work-history/work-history-form";
    }

    @PostMapping("/add")
    public String addWorkHistory(@Valid @ModelAttribute WorkHistory workHistory, BindingResult result) {
        if (result.hasErrors()) {
            return "work-history/work-history-form";
        }
        workHistoryService.createWorkHistory(workHistory);
        return "redirect:/work-history";
    }

    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<WorkHistory>> getAllWorkHistory() {
        List<WorkHistory> workHistoryList = workHistoryService.getAllWorkHistory();
        return new ResponseEntity<>(workHistoryList, HttpStatus.OK);
    }

    @GetMapping("/paginated")
    @ResponseBody
    public ResponseEntity<Page<WorkHistory>> getPaginatedWorkHistory(Pageable pageable) {
        Page<WorkHistory> workHistoryPage = workHistoryService.getWorkHistoryPaginated(pageable);
        return new ResponseEntity<>(workHistoryPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<WorkHistory> getWorkHistoryById(@PathVariable Long id) {
        return workHistoryService.getWorkHistoryById(id)
                .map(workHistory -> new ResponseEntity<>(workHistory, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<WorkHistory> updateWorkHistory(@PathVariable Long id, @Valid @RequestBody WorkHistory workHistory) {
        WorkHistory updatedWorkHistory = workHistoryService.updateWorkHistoryById(id, workHistory);
        return new ResponseEntity<>(updatedWorkHistory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteWorkHistory(@PathVariable Long id) {
        workHistoryService.deleteWorkHistoryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}