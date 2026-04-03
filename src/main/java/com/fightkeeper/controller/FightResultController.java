package com.fightkeeper.controller;

import com.fightkeeper.model.fightKeeperDB;
import com.fightkeeper.service.FightResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fight-results")
public class FightResultController {

    @Autowired
    FightResultService service;

    @GetMapping
    public ResponseEntity<List<fightKeeperDB>> getAllFightResults() {
        // Logic to retrieve all fight results
        //something simple just for testing
         return ResponseEntity.ok(service.getAllResults());
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getFightResultById(@PathVariable String id) {
        // Logic to retrieve a specific fight result by ID
        return ResponseEntity.ok("Fight result for ID: " + id);
    }

    @PostMapping
    public ResponseEntity<String> createFightResult(@RequestBody fightKeeperDB fightResult) {
        // Logic to create a new fight result
        service.saveFightResult(fightResult);
        return ResponseEntity.ok("success");
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateFightResult(@PathVariable String id, @RequestBody String fightResult) {
//        // Logic to update an existing fight result
//        return ResponseEntity.ok("Updated fight result for ID: " + id);
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteFightResult(@PathVariable String id) {
//        // Logic to delete a fight result
//        return ResponseEntity.ok("Deleted fight result for ID: " + id);
//    }
}