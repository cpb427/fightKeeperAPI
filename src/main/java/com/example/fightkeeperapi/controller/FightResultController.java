package com.example.fightkeeperapi.controller;

import com.example.fightkeeperapi.model.FightResult;
import com.example.fightkeeperapi.service.FightResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fights")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FightResultController {

    @Autowired
    private FightResultService fightResultService;

    @GetMapping
    public ResponseEntity<List<FightResult>> getAllFights() {
        List<FightResult> fights = fightResultService.getAllFights();
        return ResponseEntity.ok(fights);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FightResult> getFightById(@PathVariable String id) {
        FightResult fight = fightResultService.getFightById(id);
        if (fight != null) {
            return ResponseEntity.ok(fight);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<FightResult> createFight(@RequestBody FightResult fightResult) {
        FightResult savedFight = fightResultService.saveFight(fightResult);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFight);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FightResult> updateFight(@PathVariable String id, @RequestBody FightResult fightResult) {
        fightResult.setId(id);
        FightResult updatedFight = fightResultService.updateFight(fightResult);
        if (updatedFight != null) {
            return ResponseEntity.ok(updatedFight);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFight(@PathVariable String id) {
        fightResultService.deleteFight(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/game/{game}")
    public ResponseEntity<List<FightResult>> getFightsByGame(@PathVariable String game) {
        List<FightResult> fights = fightResultService.getFightsByGame(game);
        return ResponseEntity.ok(fights);
    }
}