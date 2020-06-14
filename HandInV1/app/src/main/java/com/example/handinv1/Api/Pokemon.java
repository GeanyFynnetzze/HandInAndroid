package com.example.handinv1.Api;

import java.util.ArrayList;

public class Pokemon {
    private int number;
    private String name;
    private int height;
    private int weight;
    private String imageUrl;

    public Pokemon(int number, String name, int height, int weight, String imageUrl) {
        this.number = number;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
