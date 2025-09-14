package com.wrestlingapp.repository;

import com.wrestlingapp.entity.Wrestler;
import com.wrestlingapp.entity.WeightCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WrestlerRepository extends JpaRepository<Wrestler, Long> {
    
    List<Wrestler> findByWeightCategory(WeightCategory weightCategory);
    
    List<Wrestler> findByNationality(String nationality);
    
    List<Wrestler> findByClub(String club);
    
    Page<Wrestler> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName, Pageable pageable);
    
    @Query("SELECT w FROM Wrestler w WHERE w.weightCategory = :category ORDER BY w.ranking ASC")
    List<Wrestler> findByWeightCategoryOrderByRanking(@Param("category") WeightCategory category);
    
    @Query("SELECT w FROM Wrestler w ORDER BY w.wins DESC, w.totalPoints DESC")
    List<Wrestler> findTopWrestlersByWins(Pageable pageable);
    
    @Query("SELECT w FROM Wrestler w ORDER BY w.totalPoints DESC")
    List<Wrestler> findTopWrestlersByPoints(Pageable pageable);
    
    @Query("SELECT w FROM Wrestler w WHERE " +
           "(w.wins + w.losses + w.draws) > 0 " +
           "ORDER BY (CAST(w.wins AS double) / (w.wins + w.losses + w.draws)) DESC")
    List<Wrestler> findTopWrestlersByWinPercentage(Pageable pageable);
    
    Optional<Wrestler> findByFirstNameAndLastNameAndNationality(
            String firstName, String lastName, String nationality);
}