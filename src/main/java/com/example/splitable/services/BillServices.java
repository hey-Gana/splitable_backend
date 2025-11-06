package com.example.splitable.services;

import com.example.splitable.model.Bill;
import org.springframework.stereotype.Service;

@Service
public class BillServices {

    // Create a new Bill instance
    public Bill createBill(boolean taxPercent, float tax, boolean tipsPercent, float tips) {
        return new Bill(taxPercent, tax, tipsPercent, tips);
    }

    //Tax Calculation
    public float calculateTax(float subtotal, Bill bill){
        if(bill.getTaxPercent()){
            return subtotal*(bill.getTax()/100);
        }
        else {
            return bill.getTax();
        }
    }

    //Tips Calculation
    public float calculateTips(float subtotal, Bill bill) {
        if (bill.getTipsPercent()) {
            return subtotal * (bill.getTips() / 100);
        } else {
            return bill.getTips();
        }
    }

    //Grand Total Calculation
    public float calculateGrandTotal(float subtotal, Bill bill){
        float taxAmount = calculateTax(subtotal, bill);
        float tipsAmount = calculateTips(subtotal, bill);
        return subtotal + taxAmount + tipsAmount;
    }
}
