package com.wrestlingapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "wrestlers")
public class Wrestler {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(nullable = false)
    private String firstName;
    
    @NotBlank
    @Column(nullable = false)
    private String lastName;
    
    @NotNull
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    
    @NotNull
    @Column(nullable = false)
    private Double weight;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WeightCategory weightCategory;
    
    @NotBlank
    @Column(nullable = false)
    private String nationality;
    
    @Column
    private String club;
    
    @Column
    private String coach;
    
    @Column
    private Integer wins = 0;
    
    @Column
    private Integer losses = 0;
    
    @Column
    private Integer draws = 0;
    
    @Column
    private Integer totalPoints = 0;
    
    @Column
    private Integer ranking;
    
    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    @Column
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "wrestler1", cascade = CascadeType.ALL)
    private List<Match> matchesAsWrestler1;
    
    @OneToMany(mappedBy = "wrestler2", cascade = CascadeType.ALL)
    private List<Match> matchesAsWrestler2;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Constructors
    public Wrestler() {}
    
    public Wrestler(String firstName, String lastName, LocalDate dateOfBirth, 
                   Double weight, WeightCategory weightCategory, String nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.weightCategory = weightCategory;
        this.nationality = nationality;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public Double getWeight() {
        return weight;
    }
    
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    
    public WeightCategory getWeightCategory() {
        return weightCategory;
    }
    
    public void setWeightCategory(WeightCategory weightCategory) {
        this.weightCategory = weightCategory;
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public String getClub() {
        return club;
    }
    
    public void setClub(String club) {
        this.club = club;
    }
    
    public String getCoach() {
        return coach;
    }
    
    public void setCoach(String coach) {
        this.coach = coach;
    }
    
    public Integer getWins() {
        return wins;
    }
    
    public void setWins(Integer wins) {
        this.wins = wins;
    }
    
    public Integer getLosses() {
        return losses;
    }
    
    public void setLosses(Integer losses) {
        this.losses = losses;
    }
    
    public Integer getDraws() {
        return draws;
    }
    
    public void setDraws(Integer draws) {
        this.draws = draws;
    }
    
    public Integer getTotalPoints() {
        return totalPoints;
    }
    
    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }
    
    public Integer getRanking() {
        return ranking;
    }
    
    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public List<Match> getMatchesAsWrestler1() {
        return matchesAsWrestler1;
    }
    
    public void setMatchesAsWrestler1(List<Match> matchesAsWrestler1) {
        this.matchesAsWrestler1 = matchesAsWrestler1;
    }
    
    public List<Match> getMatchesAsWrestler2() {
        return matchesAsWrestler2;
    }
    
    public void setMatchesAsWrestler2(List<Match> matchesAsWrestler2) {
        this.matchesAsWrestler2 = matchesAsWrestler2;
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public double getWinPercentage() {
        int totalMatches = wins + losses + draws;
        return totalMatches > 0 ? (double) wins / totalMatches * 100 : 0.0;
    }
}