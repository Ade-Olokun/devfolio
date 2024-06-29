package com.raygraves.devfolio.controller;

import com.raygraves.devfolio.model.ContactInfo;
import com.raygraves.devfolio.service.ContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/contact-info")
public class ContactInfoController {

    private final ContactInfoService contactInfoService;

    @Autowired
    public ContactInfoController(ContactInfoService contactInfoService) {
        this.contactInfoService = contactInfoService;
    }

    @PostMapping
    public ResponseEntity<ContactInfo> createContactInfo(@Valid @RequestBody ContactInfo contactInfo) {
        ContactInfo createdContactInfo = contactInfoService.createContactInfo(contactInfo);
        return new ResponseEntity<>(createdContactInfo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ContactInfo>> getAllContactInfo() {
        List<ContactInfo> contactInfoList = contactInfoService.getAllContactInfo();
        return new ResponseEntity<>(contactInfoList, HttpStatus.OK);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<ContactInfo>> getPaginatedContactInfo(Pageable pageable) {
        Page<ContactInfo> contactInfoPage = contactInfoService.getContactInfoPaginated(pageable);
        return new ResponseEntity<>(contactInfoPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactInfo> getContactInfoById(@PathVariable Long id) {
        return contactInfoService.getContactInfoById(id)
                .map(contactInfo -> new ResponseEntity<>(contactInfo, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ContactInfo> getContactInfoByEmail(@PathVariable String email) {
        return contactInfoService.getContactInfoByEmail(email)
                .map(contactInfo -> new ResponseEntity<>(contactInfo, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ContactInfo>> searchContactInfoByName(@RequestParam String name) {
        List<ContactInfo> contactInfoList = contactInfoService.getContactInfoByNameContaining(name);
        return new ResponseEntity<>(contactInfoList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactInfo> updateContactInfo(@PathVariable Long id, @Valid @RequestBody ContactInfo contactInfo) {
        ContactInfo updatedContactInfo = contactInfoService.updateContactInfoById(id, contactInfo);
        return new ResponseEntity<>(updatedContactInfo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactInfo(@PathVariable Long id) {
        contactInfoService.deleteContactInfoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/default")
    public ResponseEntity<List<ContactInfo>> createDefaultContactInfo() {
        List<ContactInfo> defaultContactInfo = contactInfoService.createDefaultContactInfo();
        return new ResponseEntity<>(defaultContactInfo, HttpStatus.CREATED);
    }
}