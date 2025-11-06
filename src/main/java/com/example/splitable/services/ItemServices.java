package com.example.splitable.services;

import com.example.splitable.model.Items;
import com.example.splitable.model.People;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServices {
    //creating a list of items
    private List<Items> ListOfItems = new ArrayList<>();


    //Accessing People through constructor injection
    private final PeopleServices pplserv;

    public ItemServices(PeopleServices p){
        this.pplserv=p;
    }


    public Items addItems(Items i){
        //checking if People exist to tag to the item
        for(People p : i.getTaggedPeople()){
            boolean exists = pplserv.getAllPeople().stream()
                    .anyMatch(person -> person.getPid() == p.getPid());
            if(!exists){
                throw new IllegalArgumentException("Tagged Person with id"+p.getPid()+" doesn't exist");
            }

        }
        ListOfItems.add(i);
        return i;
    }
    public List<Items> getAllItems(){
        return new ArrayList<>(ListOfItems);
    }

    public float calculateSubtotal(List<Items> ListOfItems){
        float subtotal=0;
        for(Items i:ListOfItems){
            subtotal+=i.getCost();
        }
        return subtotal;
    }
}
