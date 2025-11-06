package com.example.splitable.model;

public class Bill {
    private boolean tax_percent;
    private float tax;
    private boolean tips_percent;
    private float tips;
    //constructors
    public Bill(){}
    public Bill(boolean tax_percent,float tax,boolean tips_percent,float tips){
        this.tax=tax;
        this.tax_percent=tax_percent;
        this.tips=tips;
        this.tips_percent=tips_percent;
    }
    //getters & setters
    public void setTax(float tax) {
        this.tax = tax;
    }

    public void setTax_percent(boolean tax_percent) {
        this.tax_percent = tax_percent;
    }

    public void setTips(float tips) {
        this.tips = tips;
    }

    public void setTips_percent(boolean tips_percent) {
        this.tips_percent = tips_percent;
    }

    public float getTax() {
        return tax;
    }

    public float getTips() {
        return tips;
    }

    public boolean getTaxPercent(){
        return tax_percent;
    }

    public boolean getTipsPercent(){
        return tips_percent;
    }

}
