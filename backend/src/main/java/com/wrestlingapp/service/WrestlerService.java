package com.wrestlingapp.service;

import com.wrestlingapp.entity.Wrestler;
import com.wrestlingapp.entity.WeightCategory;
import com.wrestlingapp.repository.WrestlerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WrestlerService {
    
    @Autowired
    private WrestlerRepository wrestlerRepository;
    
    public Wrestler createWrestler(Wrestler wrestler) {
        return wrestlerRepository.save(wrestler);
    }
    
    public Optional<Wrestler> getWrestlerById(Long id) {
        return wrestlerRepository.findById(id);
    }
    
    public Page<Wrestler> getAllWrestlers(Pageable pageable) {
        return wrestlerRepository.findAll(pageable);
    }
    
    public Wrestler updateWrestler(Wrestler wrestler) {
        return wrestlerRepository.save(wrestler);
    }
    
    public void deleteWrestler(Long id) {
        wrestlerRepository.deleteById(id);
    }
    
    public List<Wrestler> getWrestlersByWeightCategory(WeightCategory weightCategory) {
        return wrestlerRepository.findByWeightCategory(weightCategory);
    }
    
    public List<Wrestler> getWrestlersByNationality(String nationality) {
        return wrestlerRepository.findByNationality(nationality);
    }
    
    public List<Wrestler> getWrestlersByClub(String club) {
        return wrestlerRepository.findByClub(club);
    }
    
    public Page<Wrestler> searchWrestlers(String name, Pageable pageable) {
        return wrestlerRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                name, name, pageable);
    }
    
    public List<Wrestler> getRankingsByWeightCategory(WeightCategory weightCategory) {
        return wrestlerRepository.findByWeightCategoryOrderByRanking(weightCategory);
    }
    
    public List<Wrestler> getTopWrestlersByWins(Pageable pageable) {
        return wrestlerRepository.findTopWrestlersByWins(pageable);
    }
    
    public List<Wrestler> getTopWrestlersByPoints(Pageable pageable) {
        return wrestlerRepository.findTopWrestlersByPoints(pageable);
    }
    
    public List<Wrestler> getTopWrestlersByWinPercentage(Pageable pageable) {
        return wrestlerRepository.findTopWrestlersByWinPercentage(pageable);
    }
    
    public void updateWrestlerStats(Wrestler wrestler, boolean isWin, boolean isLoss, boolean isDraw, int points) {
        if (isWin) {
            wrestler.setWins(wrestler.getWins() + 1);
        } else if (isLoss) {
            wrestler.setLosses(wrestler.getLosses() + 1);
        } else if (isDraw) {
            wrestler.setDraws(wrestler.getDraws() + 1);
        }
        
        wrestler.setTotalPoints(wrestler.getTotalPoints() + points);
        wrestlerRepository.save(wrestler);
    }
    
    public boolean isWrestlerNameUnique(String firstName, String lastName, String nationality, Long excludeId) {
        Optional<Wrestler> existing = wrestlerRepository.findByFirstNameAndLastNameAndNationality(
                firstName, lastName, nationality);
        
        if (existing.isEmpty()) {
            return true;
        }
        
        return excludeId != null && existing.get().getId().equals(excludeId);
    }
}