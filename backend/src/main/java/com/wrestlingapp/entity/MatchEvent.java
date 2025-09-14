package com.wrestlingapp.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "match_events")
public class MatchEvent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;
    
    @Column(nullable = false)
    private LocalDateTime eventTime;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventType eventType;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wrestler_id")
    private Wrestler wrestler;
    
    @Column
    private Integer points;
    
    @Column(length = 500)
    private String description;
    
    // Constructors
    public MatchEvent() {}
    
    public MatchEvent(Match match, EventType eventType, Wrestler wrestler, Integer points) {
        this.match = match;
        this.eventType = eventType;
        this.wrestler = wrestler;
        this.points = points;
        this.eventTime = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Match getMatch() {
        return match;
    }
    
    public void setMatch(Match match) {
        this.match = match;
    }
    
    public LocalDateTime getEventTime() {
        return eventTime;
    }
    
    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }
    
    public EventType getEventType() {
        return eventType;
    }
    
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
    
    public Wrestler getWrestler() {
        return wrestler;
    }
    
    public void setWrestler(Wrestler wrestler) {
        this.wrestler = wrestler;
    }
    
    public Integer getPoints() {
        return points;
    }
    
    public void setPoints(Integer points) {
        this.points = points;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}