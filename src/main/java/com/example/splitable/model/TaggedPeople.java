package com.example.splitable.model;

public class TaggedPeople {
    private int pid;
    private int portion;

    //constructor
    public TaggedPeople(){}

    public TaggedPeople(int id, int portion){
        this.pid=id;
        this.portion=portion;
    }
    //getters & setters
    public int getPid() {
        return pid;
    }

    public int getPortion() {
        return portion;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }
}
