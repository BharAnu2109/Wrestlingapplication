package com.wrestlingapp.repository;

import com.wrestlingapp.entity.Tournament;
import com.wrestlingapp.entity.TournamentStatus;
import com.wrestlingapp.entity.WrestlingStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    
    List<Tournament> findByStatus(TournamentStatus status);
    
    List<Tournament> findByStyle(WrestlingStyle style);
    
    List<Tournament> findByCountry(String country);
    
    Page<Tournament> findByNameContainingIgnoreCase(String name, Pageable pageable);
    
    @Query("SELECT t FROM Tournament t WHERE t.startDate >= :startDate AND t.endDate <= :endDate")
    List<Tournament> findByDateRange(@Param("startDate") LocalDate startDate, 
                                   @Param("endDate") LocalDate endDate);
    
    @Query("SELECT t FROM Tournament t WHERE t.status = :status ORDER BY t.startDate ASC")
    List<Tournament> findByStatusOrderByStartDate(@Param("status") TournamentStatus status);
    
    @Query("SELECT t FROM Tournament t WHERE t.startDate > CURRENT_DATE ORDER BY t.startDate ASC")
    List<Tournament> findUpcomingTournaments();
    
    @Query("SELECT t FROM Tournament t WHERE t.endDate < CURRENT_DATE ORDER BY t.endDate DESC")
    List<Tournament> findCompletedTournaments();
}