package com.project.atmiraFCT.controller;

import com.project.atmiraFCT.exception.RecordNotFoundException;
import com.project.atmiraFCT.model.domain.Colaborator;
import com.project.atmiraFCT.repository.ColaboratorRepository;
import com.project.atmiraFCT.service.ColaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ColaboratorController {

    @Autowired
    private ColaboratorRepository colaboratorRepository;

    @Autowired
    private ColaboratorService colaboratorService;


    @PostMapping("/colaborator/save/{workplaceId}")
    public Colaborator saveColaborator(@RequestBody Colaborator colaborator, @PathVariable Long workplaceId) {
        return colaboratorService.saveColaborator(colaborator, workplaceId);
    }
    @GetMapping("/colaborator/all")
    public List<Colaborator> getAllColaborators() {
        return colaboratorRepository.findAll();
    }

    @DeleteMapping("colaborator/delete/{id}")
    public ResponseEntity<Boolean> deleteColaborator(@PathVariable String id) {
        Optional<Colaborator> result = colaboratorRepository.findById(id);
        if (result.isPresent()) {
            colaboratorRepository.deleteById(id);
            return ResponseEntity.ok(true);
        } else {
            throw new RecordNotFoundException("No colaborator found with id: " + id);
        }
    }


    @PutMapping("/colaborator/update/{id}")
    public ResponseEntity<Colaborator> updateColaborator(@PathVariable("id") String id, @RequestBody Colaborator updatedColaborator) throws Exception {
        Colaborator updated = colaboratorService.updateColaborator(id,updatedColaborator);
        return ResponseEntity.ok(updated);
    }





}
