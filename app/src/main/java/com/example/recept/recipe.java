package com.example.recept;

public class recipe {
    private String title;
    private int quality;
    private int difficulty;

    public recipe(String title, int quality, int difficulty) {
        this.title = title;
        this.quality = quality;
        this.difficulty = difficulty;
    }

    public String getTitle() {
        return title;
    }

    public int getQuality() {
        return quality;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
