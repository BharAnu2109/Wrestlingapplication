package com.wrestlingapp.entity;

public enum EventType {
    TAKEDOWN("Takedown", 2),
    ESCAPE("Escape", 1),
    REVERSAL("Reversal", 2),
    NEAR_FALL_2("Near Fall (2 points)", 2),
    NEAR_FALL_3("Near Fall (3 points)", 3),
    PENALTY("Penalty", 1),
    TECHNICAL_VIOLATION("Technical Violation", 1),
    CAUTION("Caution", 1),
    WARNING("Warning", 0),
    FALL("Fall", 0),
    DISQUALIFICATION("Disqualification", 0);
    
    private final String displayName;
    private final Integer defaultPoints;
    
    EventType(String displayName, Integer defaultPoints) {
        this.displayName = displayName;
        this.defaultPoints = defaultPoints;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public Integer getDefaultPoints() {
        return defaultPoints;
    }
}