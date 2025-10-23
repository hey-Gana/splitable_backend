package com.example.splitable.model;

public class People {
    private int pid;
    private String pname;
    //Constructors
    void people(){}
    void people(int id,String name){
        this.pid=id;
        this.pname=name;
    }
    //getters & setters

    public String getPname() {
        return pname;
    }

    public int getPid() {
        return pid;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
