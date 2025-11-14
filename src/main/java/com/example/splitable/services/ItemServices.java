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

    //function to add items
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

    //function to tag person to an item
    public Items tagPeopleToItem(int itemID, List<People> people){
        Items item=null;
        //check if item exists
        for(Items i:ListOfItems){
            if(i.getItem_id()==itemID){
                item=i;
                break;
            }
        }
        if(item==null){
            throw new IllegalArgumentException("Item not found");
        }

        //check if people exist
        List<People> checkPeople=pplserv.getAllPeople();
        for(People p:people){
            boolean exists = false;
            for(People chk:checkPeople){
                if(chk.getPid()== p.getPid()){
                    exists=true;
                }
            }

            if (!exists){
                throw new IllegalArgumentException("Tagged person with id " + p.getPid() + " doesn't exist");
            }
        }

        //add person, without duplicates
        for (People p:people){
            boolean alreadyTagged=false;
            for (People existing:item.getTaggedPeople()){
                if(existing.getPid()==p.getPid()){
                    alreadyTagged=true;
                }
            }
            if (!alreadyTagged) {
                item.getTaggedPeople().add(p);
            }
        }
        return item;

    }


    //function to remove tagged people from an item
    public Items removePeopleFromItem(int itemID,People person){
        //check item
        Items item=null;
        for(Items i:ListOfItems){
            if(i.getItem_id()==itemID){
                item=i;
                break;
            }
        }
        if(item==null){
            throw new IllegalArgumentException("Item not found!");
        }

        // remove the person by pid
        item.getTaggedPeople().removeIf(p -> p.getPid() == person.getPid());

        return item;
    }




    public float calculateSubtotal(List<Items> ListOfItems){
        float subtotal=0;
        for(Items i:ListOfItems){
            subtotal+=i.getCost();
        }
        return subtotal;
    }
}
