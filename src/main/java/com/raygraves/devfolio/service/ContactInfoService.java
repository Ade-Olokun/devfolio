package com.raygraves.devfolio.service;

import com.raygraves.devfolio.model.ContactInfo;
import com.raygraves.devfolio.repository.ContactInfoRepository;
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
public class ContactInfoService {

    private final ContactInfoRepository contactInfoRepository;

    @Autowired
    public ContactInfoService(ContactInfoRepository contactInfoRepository) {
        this.contactInfoRepository = contactInfoRepository;
    }

    // Create operations
    public ContactInfo createContactInfo(ContactInfo contactInfo) {
        return contactInfoRepository.save(contactInfo);
    }

    public List<ContactInfo> createMultipleContactInfo(List<ContactInfo> contactInfoList) {
        return contactInfoRepository.saveAll(contactInfoList);
    }

    // Read operations
    public List<ContactInfo> getAllContactInfo() {
        return contactInfoRepository.findAll();
    }

    public Optional<ContactInfo> getContactInfoById(Long id) {
        return contactInfoRepository.findById(id);
    }

    public Page<ContactInfo> getContactInfoPaginated(Pageable pageable) {
        return contactInfoRepository.findAll(pageable);
    }

    public Optional<ContactInfo> getContactInfoByEmail(String email) {
        return contactInfoRepository.findByEmail(email);
    }

    public List<ContactInfo> getContactInfoByNameContaining(String name) {
        return contactInfoRepository.findByFullNameContaining(name);
    }

    // Update operations
    public ContactInfo updateContactInfo(ContactInfo contactInfo) {
        return contactInfoRepository.save(contactInfo);
    }

    public ContactInfo updateContactInfoById(Long id, ContactInfo updatedContactInfo) {
        return contactInfoRepository.findById(id)
                .map(existingContactInfo -> {
                    existingContactInfo.setFullName(updatedContactInfo.getFullName());
                    existingContactInfo.setEmail(updatedContactInfo.getEmail());
                    existingContactInfo.setPhone(updatedContactInfo.getPhone());
                    existingContactInfo.setAddress(updatedContactInfo.getAddress());
                    existingContactInfo.setLinkedInUrl(updatedContactInfo.getLinkedInUrl());
                    existingContactInfo.setGithubUrl(updatedContactInfo.getGithubUrl());
                    return contactInfoRepository.save(existingContactInfo);
                })
                .orElseGet(() -> {
                    updatedContactInfo.setId(id);
                    return contactInfoRepository.save(updatedContactInfo);
                });
    }

    // Delete operations
    public void deleteContactInfo(ContactInfo contactInfo) {
        contactInfoRepository.delete(contactInfo);
    }

    public void deleteContactInfoById(Long id) {
        contactInfoRepository.deleteById(id);
    }

    public void deleteAllContactInfo() {
        contactInfoRepository.deleteAll();
    }

    // Utility methods
    public long countContactInfo() {
        return contactInfoRepository.count();
    }

    public boolean existsContactInfoById(Long id) {
        return contactInfoRepository.existsById(id);
    }

    // Method to create default data
    public List<ContactInfo> createDefaultContactInfo() {
        List<ContactInfo> defaultContactInfoList = new ArrayList<>();

        defaultContactInfoList.add(ContactInfo.builder()
                .fullName("John Doe")
                .email("john.doe@example.com")
                .phone("123-456-7890")
                .address("123 Main St, Anytown, USA")
                .linkedInUrl("https://www.linkedin.com/in/johndoe")
                .githubUrl("https://github.com/johndoe")
                .build());

        defaultContactInfoList.add(ContactInfo.builder()
                .fullName("Jane Smith")
                .email("jane.smith@example.com")
                .phone("987-654-3210")
                .address("456 Oak Ave, Another City, USA")
                .linkedInUrl("https://www.linkedin.com/in/janesmith")
                .githubUrl("https://github.com/janesmith")
                .build());

        return contactInfoRepository.saveAll(defaultContactInfoList);
    }
}