package com.example.splitable.controller;

import com.example.splitable.model.Bill;
import com.example.splitable.model.Items;
import com.example.splitable.services.BillServices;
import com.example.splitable.services.ItemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillServices billServices;

    @Autowired
    private ItemServices itemServices;


    @GetMapping("/total")
    public float calculateGrandTotal(@RequestBody Bill bill){
        List<Items> items = itemServices.getAllItems();
        float subtotal = itemServices.calculateSubtotal(items); 
        return billServices.calculateGrandTotal(subtotal, bill);
    }


    @PostMapping("/add")
    public Bill createBill(@RequestBody Bill bill){
        return billServices.createBill(bill.getTaxPercent(), bill.getTax(), bill.getTipsPercent(), bill.getTips());
    }


}
