package com.raygraves.devfolio.controller;

import com.raygraves.devfolio.model.ContactInfo;
import com.raygraves.devfolio.service.ContactInfoService;
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
@RequestMapping("/contact-info")
public class ContactInfoController {

    private final ContactInfoService contactInfoService;

    @Autowired
    public ContactInfoController(ContactInfoService contactInfoService) {
        this.contactInfoService = contactInfoService;
    }

    // Handler for displaying the form
    @GetMapping("/add")
    public String showContactInfoForm(Model model) {
        model.addAttribute("contactInfo", new ContactInfo());
        return "contact-info/contact-info-form";
    }

    // Handler for form submission
    @PostMapping("/add")
    public String addContactInfo(@Valid @ModelAttribute ContactInfo contactInfo, BindingResult result) {
        if (result.hasErrors()) {
            return "contact-info/contact-info-form";
        }
        contactInfoService.createContactInfo(contactInfo);
        return "redirect:/";
    }

    // REST API endpoints

    @PostMapping
    @ResponseBody
    public ResponseEntity<ContactInfo> createContactInfo(@Valid @RequestBody ContactInfo contactInfo) {
        ContactInfo createdContactInfo = contactInfoService.createContactInfo(contactInfo);
        return new ResponseEntity<>(createdContactInfo, HttpStatus.CREATED);
    }

    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<ContactInfo>> getAllContactInfo() {
        List<ContactInfo> contactInfoList = contactInfoService.getAllContactInfo();
        return new ResponseEntity<>(contactInfoList, HttpStatus.OK);
    }

    @GetMapping("/paginated")
    @ResponseBody
    public ResponseEntity<Page<ContactInfo>> getPaginatedContactInfo(Pageable pageable) {
        Page<ContactInfo> contactInfoPage = contactInfoService.getContactInfoPaginated(pageable);
        return new ResponseEntity<>(contactInfoPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ContactInfo> getContactInfoById(@PathVariable Long id) {
        return contactInfoService.getContactInfoById(id)
                .map(contactInfo -> new ResponseEntity<>(contactInfo, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/email/{email}")
    @ResponseBody
    public ResponseEntity<ContactInfo> getContactInfoByEmail(@PathVariable String email) {
        return contactInfoService.getContactInfoByEmail(email)
                .map(contactInfo -> new ResponseEntity<>(contactInfo, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<List<ContactInfo>> searchContactInfoByName(@RequestParam String name) {
        List<ContactInfo> contactInfoList = contactInfoService.getContactInfoByNameContaining(name);
        return new ResponseEntity<>(contactInfoList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ContactInfo> updateContactInfo(@PathVariable Long id, @Valid @RequestBody ContactInfo contactInfo) {
        ContactInfo updatedContactInfo = contactInfoService.updateContactInfoById(id, contactInfo);
        return new ResponseEntity<>(updatedContactInfo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteContactInfo(@PathVariable Long id) {
        contactInfoService.deleteContactInfoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/default")
    @ResponseBody
    public ResponseEntity<List<ContactInfo>> createDefaultContactInfo() {
        List<ContactInfo> defaultContactInfo = contactInfoService.createDefaultContactInfo();
        return new ResponseEntity<>(defaultContactInfo, HttpStatus.CREATED);
    }
    @GetMapping
    public String listContactInfo(Model model) {
        List<ContactInfo> contactInfoList = contactInfoService.getAllContactInfo();
        if (contactInfoList.isEmpty()) {
            contactInfoList = contactInfoService.createDefaultContactInfo();
        }
        model.addAttribute("contactInfoList", contactInfoList);
        return "contact-info/contact-info-list";
    }
}