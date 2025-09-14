package com.wrestlingapp.entity;

public enum WrestlingStyle {
    FREESTYLE("Freestyle"),
    GRECO_ROMAN("Greco-Roman"),
    FOLKSTYLE("Folkstyle");
    
    private final String displayName;
    
    WrestlingStyle(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}