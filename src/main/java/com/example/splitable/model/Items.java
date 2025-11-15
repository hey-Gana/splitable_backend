package com.example.splitable.model;

import java.util.ArrayList;

public class Items {
    private int item_id;
    private String ItemName;
    private float cost;
    private ArrayList<TaggedPeople> taggedPeople = new ArrayList<>();
    //constructors
    public Items(){}
    public Items(int id, String name, float cost, ArrayList<TaggedPeople> taggedpeople){
        this.item_id=id;
        this.ItemName=name;
        this.cost=cost;
        this.taggedPeople=taggedpeople;
    }

    //getters & setters
    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public void setTaggedPeople(ArrayList<TaggedPeople> taggedPeople) {
        this.taggedPeople = taggedPeople;
    }

    public int getItem_id() {
        return item_id;
    }

    public String getItemName() {
        return ItemName;
    }

    public float getCost() {
        return cost;
    }

    public ArrayList<TaggedPeople> getTaggedPeople() {
        return taggedPeople;
    }
}
