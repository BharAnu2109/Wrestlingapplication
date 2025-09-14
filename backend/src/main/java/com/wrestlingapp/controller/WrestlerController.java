package com.wrestlingapp.controller;

import com.wrestlingapp.entity.Wrestler;
import com.wrestlingapp.entity.WeightCategory;
import com.wrestlingapp.service.WrestlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wrestlers")
@CrossOrigin(origins = "*")
public class WrestlerController {
    
    @Autowired
    private WrestlerService wrestlerService;
    
    @PostMapping
    public ResponseEntity<Wrestler> createWrestler(@Valid @RequestBody Wrestler wrestler) {
        try {
            if (!wrestlerService.isWrestlerNameUnique(wrestler.getFirstName(), 
                    wrestler.getLastName(), wrestler.getNationality(), null)) {
                return ResponseEntity.badRequest().build();
            }
            
            Wrestler createdWrestler = wrestlerService.createWrestler(wrestler);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWrestler);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<Page<Wrestler>> getAllWrestlers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
                Sort.by(sortBy).descending() : 
                Sort.by(sortBy).ascending();
        
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Wrestler> wrestlers = wrestlerService.getAllWrestlers(pageable);
        return ResponseEntity.ok(wrestlers);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Wrestler> getWrestlerById(@PathVariable Long id) {
        Optional<Wrestler> wrestler = wrestlerService.getWrestlerById(id);
        return wrestler.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Wrestler> updateWrestler(@PathVariable Long id, 
                                                  @Valid @RequestBody Wrestler wrestlerDetails) {
        Optional<Wrestler> wrestlerOpt = wrestlerService.getWrestlerById(id);
        if (wrestlerOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        if (!wrestlerService.isWrestlerNameUnique(wrestlerDetails.getFirstName(), 
                wrestlerDetails.getLastName(), wrestlerDetails.getNationality(), id)) {
            return ResponseEntity.badRequest().build();
        }
        
        Wrestler wrestler = wrestlerOpt.get();
        wrestler.setFirstName(wrestlerDetails.getFirstName());
        wrestler.setLastName(wrestlerDetails.getLastName());
        wrestler.setDateOfBirth(wrestlerDetails.getDateOfBirth());
        wrestler.setWeight(wrestlerDetails.getWeight());
        wrestler.setWeightCategory(wrestlerDetails.getWeightCategory());
        wrestler.setNationality(wrestlerDetails.getNationality());
        wrestler.setClub(wrestlerDetails.getClub());
        wrestler.setCoach(wrestlerDetails.getCoach());
        
        Wrestler updatedWrestler = wrestlerService.updateWrestler(wrestler);
        return ResponseEntity.ok(updatedWrestler);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWrestler(@PathVariable Long id) {
        if (wrestlerService.getWrestlerById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        wrestlerService.deleteWrestler(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/search")
    public ResponseEntity<Page<Wrestler>> searchWrestlers(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Wrestler> wrestlers = wrestlerService.searchWrestlers(name, pageable);
        return ResponseEntity.ok(wrestlers);
    }
    
    @GetMapping("/weight-category/{category}")
    public ResponseEntity<List<Wrestler>> getWrestlersByWeightCategory(@PathVariable WeightCategory category) {
        List<Wrestler> wrestlers = wrestlerService.getWrestlersByWeightCategory(category);
        return ResponseEntity.ok(wrestlers);
    }
    
    @GetMapping("/nationality/{nationality}")
    public ResponseEntity<List<Wrestler>> getWrestlersByNationality(@PathVariable String nationality) {
        List<Wrestler> wrestlers = wrestlerService.getWrestlersByNationality(nationality);
        return ResponseEntity.ok(wrestlers);
    }
    
    @GetMapping("/club/{club}")
    public ResponseEntity<List<Wrestler>> getWrestlersByClub(@PathVariable String club) {
        List<Wrestler> wrestlers = wrestlerService.getWrestlersByClub(club);
        return ResponseEntity.ok(wrestlers);
    }
    
    @GetMapping("/rankings/{category}")
    public ResponseEntity<List<Wrestler>> getRankingsByWeightCategory(@PathVariable WeightCategory category) {
        List<Wrestler> wrestlers = wrestlerService.getRankingsByWeightCategory(category);
        return ResponseEntity.ok(wrestlers);
    }
    
    @GetMapping("/top/wins")
    public ResponseEntity<List<Wrestler>> getTopWrestlersByWins(
            @RequestParam(defaultValue = "10") int limit) {
        
        Pageable pageable = PageRequest.of(0, limit);
        List<Wrestler> wrestlers = wrestlerService.getTopWrestlersByWins(pageable);
        return ResponseEntity.ok(wrestlers);
    }
    
    @GetMapping("/top/points")
    public ResponseEntity<List<Wrestler>> getTopWrestlersByPoints(
            @RequestParam(defaultValue = "10") int limit) {
        
        Pageable pageable = PageRequest.of(0, limit);
        List<Wrestler> wrestlers = wrestlerService.getTopWrestlersByPoints(pageable);
        return ResponseEntity.ok(wrestlers);
    }
    
    @GetMapping("/top/win-percentage")
    public ResponseEntity<List<Wrestler>> getTopWrestlersByWinPercentage(
            @RequestParam(defaultValue = "10") int limit) {
        
        Pageable pageable = PageRequest.of(0, limit);
        List<Wrestler> wrestlers = wrestlerService.getTopWrestlersByWinPercentage(pageable);
        return ResponseEntity.ok(wrestlers);
    }
}