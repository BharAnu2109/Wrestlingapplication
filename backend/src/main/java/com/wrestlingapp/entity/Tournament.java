package com.wrestlingapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tournaments")
public class Tournament {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(nullable = false)
    private String name;
    
    @Column(length = 1000)
    private String description;
    
    @NotNull
    @Column(nullable = false)
    private LocalDate startDate;
    
    @NotNull
    @Column(nullable = false)
    private LocalDate endDate;
    
    @Column
    private String venue;
    
    @Column
    private String city;
    
    @Column
    private String country;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TournamentStatus status;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WrestlingStyle style;
    
    @Column
    private Integer maxParticipants;
    
    @Column
    private Double entryFee;
    
    @Column
    private Double prizeMoney;
    
    @Column
    private String organizer;
    
    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private List<Match> matches;
    
    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    @Column
    private LocalDateTime updatedAt;
    
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
    public Tournament() {}
    
    public Tournament(String name, LocalDate startDate, LocalDate endDate, WrestlingStyle style) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.style = style;
        this.status = TournamentStatus.UPCOMING;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public String getVenue() {
        return venue;
    }
    
    public void setVenue(String venue) {
        this.venue = venue;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public TournamentStatus getStatus() {
        return status;
    }
    
    public void setStatus(TournamentStatus status) {
        this.status = status;
    }
    
    public WrestlingStyle getStyle() {
        return style;
    }
    
    public void setStyle(WrestlingStyle style) {
        this.style = style;
    }
    
    public Integer getMaxParticipants() {
        return maxParticipants;
    }
    
    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }
    
    public Double getEntryFee() {
        return entryFee;
    }
    
    public void setEntryFee(Double entryFee) {
        this.entryFee = entryFee;
    }
    
    public Double getPrizeMoney() {
        return prizeMoney;
    }
    
    public void setPrizeMoney(Double prizeMoney) {
        this.prizeMoney = prizeMoney;
    }
    
    public String getOrganizer() {
        return organizer;
    }
    
    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }
    
    public List<Match> getMatches() {
        return matches;
    }
    
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}