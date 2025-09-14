package com.wrestlingapp.repository;

import com.wrestlingapp.entity.Match;
import com.wrestlingapp.entity.MatchStatus;
import com.wrestlingapp.entity.Tournament;
import com.wrestlingapp.entity.Wrestler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    
    List<Match> findByWrestler1OrWrestler2(Wrestler wrestler1, Wrestler wrestler2);
    
    List<Match> findByTournament(Tournament tournament);
    
    List<Match> findByStatus(MatchStatus status);
    
    Page<Match> findByMatchDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    
    @Query("SELECT m FROM Match m WHERE m.wrestler1 = :wrestler OR m.wrestler2 = :wrestler ORDER BY m.matchDate DESC")
    List<Match> findByWrestlerOrderByMatchDateDesc(@Param("wrestler") Wrestler wrestler);
    
    @Query("SELECT m FROM Match m WHERE (m.wrestler1 = :wrestler1 AND m.wrestler2 = :wrestler2) OR " +
           "(m.wrestler1 = :wrestler2 AND m.wrestler2 = :wrestler1) ORDER BY m.matchDate DESC")
    List<Match> findMatchesBetweenWrestlers(@Param("wrestler1") Wrestler wrestler1, 
                                          @Param("wrestler2") Wrestler wrestler2);
    
    @Query("SELECT m FROM Match m WHERE m.status = :status AND m.matchDate BETWEEN :startDate AND :endDate")
    List<Match> findByStatusAndDateRange(@Param("status") MatchStatus status,
                                       @Param("startDate") LocalDateTime startDate,
                                       @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT COUNT(m) FROM Match m WHERE m.wrestler1 = :wrestler OR m.wrestler2 = :wrestler")
    Long countMatchesByWrestler(@Param("wrestler") Wrestler wrestler);
    
    @Query("SELECT COUNT(m) FROM Match m WHERE m.winner = :wrestler")
    Long countWinsByWrestler(@Param("wrestler") Wrestler wrestler);
}