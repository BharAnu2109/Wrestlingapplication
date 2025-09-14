package com.wrestlingapp.service;

import com.wrestlingapp.entity.*;
import com.wrestlingapp.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MatchService {
    
    @Autowired
    private MatchRepository matchRepository;
    
    @Autowired
    private WrestlerService wrestlerService;
    
    public Match createMatch(Match match) {
        match.setStatus(MatchStatus.SCHEDULED);
        return matchRepository.save(match);
    }
    
    public Optional<Match> getMatchById(Long id) {
        return matchRepository.findById(id);
    }
    
    public Page<Match> getAllMatches(Pageable pageable) {
        return matchRepository.findAll(pageable);
    }
    
    public Match updateMatch(Match match) {
        return matchRepository.save(match);
    }
    
    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }
    
    public List<Match> getMatchesByWrestler(Wrestler wrestler) {
        return matchRepository.findByWrestlerOrderByMatchDateDesc(wrestler);
    }
    
    public List<Match> getMatchesByTournament(Tournament tournament) {
        return matchRepository.findByTournament(tournament);
    }
    
    public List<Match> getMatchesByStatus(MatchStatus status) {
        return matchRepository.findByStatus(status);
    }
    
    public Page<Match> getMatchesByDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return matchRepository.findByMatchDateBetween(startDate, endDate, pageable);
    }
    
    public List<Match> getMatchesBetweenWrestlers(Wrestler wrestler1, Wrestler wrestler2) {
        return matchRepository.findMatchesBetweenWrestlers(wrestler1, wrestler2);
    }
    
    public Match startMatch(Long matchId) {
        Optional<Match> matchOpt = matchRepository.findById(matchId);
        if (matchOpt.isPresent()) {
            Match match = matchOpt.get();
            match.setStatus(MatchStatus.IN_PROGRESS);
            return matchRepository.save(match);
        }
        throw new RuntimeException("Match not found");
    }
    
    public Match completeMatch(Long matchId, Wrestler winner, Integer wrestler1Score, 
                              Integer wrestler2Score, MatchResult result) {
        Optional<Match> matchOpt = matchRepository.findById(matchId);
        if (matchOpt.isPresent()) {
            Match match = matchOpt.get();
            match.setStatus(MatchStatus.COMPLETED);
            match.setWinner(winner);
            match.setWrestler1Score(wrestler1Score);
            match.setWrestler2Score(wrestler2Score);
            match.setResult(result);
            
            Match savedMatch = matchRepository.save(match);
            
            // Update wrestler statistics
            updateWrestlerStatsAfterMatch(match, wrestler1Score, wrestler2Score, winner);
            
            return savedMatch;
        }
        throw new RuntimeException("Match not found");
    }
    
    private void updateWrestlerStatsAfterMatch(Match match, Integer wrestler1Score, 
                                             Integer wrestler2Score, Wrestler winner) {
        Wrestler wrestler1 = match.getWrestler1();
        Wrestler wrestler2 = match.getWrestler2();
        
        boolean wrestler1Wins = winner != null && winner.getId().equals(wrestler1.getId());
        boolean wrestler2Wins = winner != null && winner.getId().equals(wrestler2.getId());
        boolean isDraw = winner == null;
        
        // Update wrestler1 stats
        wrestlerService.updateWrestlerStats(wrestler1, wrestler1Wins, wrestler2Wins, isDraw, wrestler1Score);
        
        // Update wrestler2 stats
        wrestlerService.updateWrestlerStats(wrestler2, wrestler2Wins, wrestler1Wins, isDraw, wrestler2Score);
    }
    
    public Long getTotalMatchesByWrestler(Wrestler wrestler) {
        return matchRepository.countMatchesByWrestler(wrestler);
    }
    
    public Long getTotalWinsByWrestler(Wrestler wrestler) {
        return matchRepository.countWinsByWrestler(wrestler);
    }
}