package com.wrestlingapp.entity;

public enum MatchResult {
    WIN_BY_FALL("Win by Fall"),
    WIN_BY_TECHNICAL_FALL("Win by Technical Fall"),
    WIN_BY_DECISION("Win by Decision"),
    WIN_BY_DISQUALIFICATION("Win by Disqualification"),
    WIN_BY_FORFEIT("Win by Forfeit"),
    DRAW("Draw"),
    NO_CONTEST("No Contest");
    
    private final String displayName;
    
    MatchResult(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}