package com.fightkeeper.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fight-results")
public class FightResultController {

    @GetMapping
    public ResponseEntity<String> getAllFightResults() {
        // Logic to retrieve all fight results
        return ResponseEntity.ok("All fight results");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getFightResultById(@PathVariable String id) {
        // Logic to retrieve a specific fight result by ID
        return ResponseEntity.ok("Fight result for ID: " + id);
    }

    @PostMapping
    public ResponseEntity<String> createFightResult(@RequestBody String fightResult) {
        // Logic to create a new fight result
        return ResponseEntity.ok("Created fight result: " + fightResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFightResult(@PathVariable String id, @RequestBody String fightResult) {
        // Logic to update an existing fight result
        return ResponseEntity.ok("Updated fight result for ID: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFightResult(@PathVariable String id) {
        // Logic to delete a fight result
        return ResponseEntity.ok("Deleted fight result for ID: " + id);
    }
}