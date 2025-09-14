package com.wrestlingapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "matches")
public class Match {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wrestler1_id", nullable = false)
    private Wrestler wrestler1;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wrestler2_id", nullable = false)
    private Wrestler wrestler2;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
    
    @Column(nullable = false)
    private LocalDateTime matchDate;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MatchStatus status;
    
    @Enumerated(EnumType.STRING)
    private MatchResult result;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner_id")
    private Wrestler winner;
    
    @Column
    private Integer wrestler1Score = 0;
    
    @Column
    private Integer wrestler2Score = 0;
    
    @Column
    private Integer duration; // in seconds
    
    @Enumerated(EnumType.STRING)
    private WrestlingStyle style;
    
    @Column
    private String venue;
    
    @Column
    private String referee;
    
    @Column(length = 1000)
    private String notes;
    
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatchEvent> events;
    
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
    public Match() {}
    
    public Match(Wrestler wrestler1, Wrestler wrestler2, LocalDateTime matchDate, WrestlingStyle style) {
        this.wrestler1 = wrestler1;
        this.wrestler2 = wrestler2;
        this.matchDate = matchDate;
        this.style = style;
        this.status = MatchStatus.SCHEDULED;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Wrestler getWrestler1() {
        return wrestler1;
    }
    
    public void setWrestler1(Wrestler wrestler1) {
        this.wrestler1 = wrestler1;
    }
    
    public Wrestler getWrestler2() {
        return wrestler2;
    }
    
    public void setWrestler2(Wrestler wrestler2) {
        this.wrestler2 = wrestler2;
    }
    
    public Tournament getTournament() {
        return tournament;
    }
    
    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
    
    public LocalDateTime getMatchDate() {
        return matchDate;
    }
    
    public void setMatchDate(LocalDateTime matchDate) {
        this.matchDate = matchDate;
    }
    
    public MatchStatus getStatus() {
        return status;
    }
    
    public void setStatus(MatchStatus status) {
        this.status = status;
    }
    
    public MatchResult getResult() {
        return result;
    }
    
    public void setResult(MatchResult result) {
        this.result = result;
    }
    
    public Wrestler getWinner() {
        return winner;
    }
    
    public void setWinner(Wrestler winner) {
        this.winner = winner;
    }
    
    public Integer getWrestler1Score() {
        return wrestler1Score;
    }
    
    public void setWrestler1Score(Integer wrestler1Score) {
        this.wrestler1Score = wrestler1Score;
    }
    
    public Integer getWrestler2Score() {
        return wrestler2Score;
    }
    
    public void setWrestler2Score(Integer wrestler2Score) {
        this.wrestler2Score = wrestler2Score;
    }
    
    public Integer getDuration() {
        return duration;
    }
    
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    
    public WrestlingStyle getStyle() {
        return style;
    }
    
    public void setStyle(WrestlingStyle style) {
        this.style = style;
    }
    
    public String getVenue() {
        return venue;
    }
    
    public void setVenue(String venue) {
        this.venue = venue;
    }
    
    public String getReferee() {
        return referee;
    }
    
    public void setReferee(String referee) {
        this.referee = referee;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public List<MatchEvent> getEvents() {
        return events;
    }
    
    public void setEvents(List<MatchEvent> events) {
        this.events = events;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}