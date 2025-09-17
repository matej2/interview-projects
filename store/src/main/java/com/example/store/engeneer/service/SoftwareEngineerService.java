package com.example.store.engeneer.service;

import com.example.store.engeneer.model.SoftwareEngineer;
import com.example.store.engeneer.repository.SoftwareEngineerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareEngineerService {
    private final SoftwareEngineerRepository softwareEngineerRepository;

    public SoftwareEngineerService(SoftwareEngineerRepository softwareEngineerRepository) {
        this.softwareEngineerRepository = softwareEngineerRepository;
    }

    public List<SoftwareEngineer> getAllSoftwareEngineers() {
        // Example
        return softwareEngineerRepository.findAll();
    }

    public void addSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        softwareEngineerRepository.save(softwareEngineer);
    }

    public SoftwareEngineer getSoftwareEngineerById(Integer id) {
        return softwareEngineerRepository
                .findById(id)
                .orElseThrow(() ->
                    new IllegalArgumentException(String.format("%s not found", id))
                );
    }
}
