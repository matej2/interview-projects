package com.example.store.engeneer.controller;

import com.example.store.engeneer.model.SoftwareEngineer;
import com.example.store.engeneer.service.SoftwareEngineerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {

    private final SoftwareEngineerService softwareEngineerService;

    public SoftwareEngineerController(SoftwareEngineerService softwareEngineerService) {
        this.softwareEngineerService = softwareEngineerService;
    }

    @GetMapping
    public List<SoftwareEngineer> getExampleData() {
        return softwareEngineerService.getAllSoftwareEngineers();
    }

    @GetMapping("{id}")
    public SoftwareEngineer getSoftwareEngineerById(@PathVariable Integer id) {
        return softwareEngineerService.getSoftwareEngineerById(id );
    }

    @PostMapping
    public void addEngineer(@RequestBody SoftwareEngineer softwareEngineer) {
        softwareEngineerService.addSoftwareEngineer(softwareEngineer);
    }
}
