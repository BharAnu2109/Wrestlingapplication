package com.wrestlingapp.entity;

public enum WeightCategory {
    // Men's Freestyle and Greco-Roman
    MEN_57KG("Men's 57kg", 57.0),
    MEN_61KG("Men's 61kg", 61.0),
    MEN_65KG("Men's 65kg", 65.0),
    MEN_70KG("Men's 70kg", 70.0),
    MEN_74KG("Men's 74kg", 74.0),
    MEN_79KG("Men's 79kg", 79.0),
    MEN_86KG("Men's 86kg", 86.0),
    MEN_92KG("Men's 92kg", 92.0),
    MEN_97KG("Men's 97kg", 97.0),
    MEN_125KG("Men's 125kg", 125.0),
    
    // Women's Freestyle
    WOMEN_50KG("Women's 50kg", 50.0),
    WOMEN_53KG("Women's 53kg", 53.0),
    WOMEN_55KG("Women's 55kg", 55.0),
    WOMEN_57KG("Women's 57kg", 57.0),
    WOMEN_59KG("Women's 59kg", 59.0),
    WOMEN_62KG("Women's 62kg", 62.0),
    WOMEN_65KG("Women's 65kg", 65.0),
    WOMEN_68KG("Women's 68kg", 68.0),
    WOMEN_72KG("Women's 72kg", 72.0),
    WOMEN_76KG("Women's 76kg", 76.0);
    
    private final String displayName;
    private final Double maxWeight;
    
    WeightCategory(String displayName, Double maxWeight) {
        this.displayName = displayName;
        this.maxWeight = maxWeight;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public Double getMaxWeight() {
        return maxWeight;
    }
    
    public static WeightCategory determineCategory(Double weight, boolean isMale) {
        if (isMale) {
            if (weight <= 57.0) return MEN_57KG;
            if (weight <= 61.0) return MEN_61KG;
            if (weight <= 65.0) return MEN_65KG;
            if (weight <= 70.0) return MEN_70KG;
            if (weight <= 74.0) return MEN_74KG;
            if (weight <= 79.0) return MEN_79KG;
            if (weight <= 86.0) return MEN_86KG;
            if (weight <= 92.0) return MEN_92KG;
            if (weight <= 97.0) return MEN_97KG;
            return MEN_125KG;
        } else {
            if (weight <= 50.0) return WOMEN_50KG;
            if (weight <= 53.0) return WOMEN_53KG;
            if (weight <= 55.0) return WOMEN_55KG;
            if (weight <= 57.0) return WOMEN_57KG;
            if (weight <= 59.0) return WOMEN_59KG;
            if (weight <= 62.0) return WOMEN_62KG;
            if (weight <= 65.0) return WOMEN_65KG;
            if (weight <= 68.0) return WOMEN_68KG;
            if (weight <= 72.0) return WOMEN_72KG;
            return WOMEN_76KG;
        }
    }
}