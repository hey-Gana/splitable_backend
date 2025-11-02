package com.example.splitable.services;

import com.example.splitable.model.Items;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServices {
    //creating a list of items
    private List<Items> ListOfItems = new ArrayList<>();

    public Items addItems(Items i){
        ListOfItems.add(i);
        return i;
    }
    public List<Items> getAllItems(){
        return new ArrayList<>(ListOfItems);
    }
}
