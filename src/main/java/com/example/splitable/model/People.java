package com.example.splitable.model;

public class People {
    private int pid;
    private String pname;
    private float split_amount;
    //Constructors
    public People(){}
    public People(int id,String name, float amount){
        this.pid=id;
        this.pname=name;
        this.split_amount=amount;
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

    public float getAmount() {
        return split_amount;
    }

    public void setAmount(float amount) {
        this.split_amount = amount;
    }
}
