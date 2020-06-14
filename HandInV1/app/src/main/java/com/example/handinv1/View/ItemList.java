package com.example.handinv1.View;

public class ItemList {
    private String itemName;
    private String itemEffect;
    private int iconId;

    public ItemList(String itemName, String itemEffect, int iconId) {
        this.itemName = itemName;
        this.itemEffect = itemEffect;
        this.iconId = iconId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemEffect() {
        return itemEffect;
    }

    public int getIconId() {
        return iconId;
    }
}
