package com.example.splitable.controller;

import com.example.splitable.model.Items;
import com.example.splitable.model.People;
import com.example.splitable.model.TaggedPeople;
import com.example.splitable.services.ItemServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsController {
    private final ItemServices itmserv;

    //constructor injection
    public ItemsController(ItemServices itmserv){
        this.itmserv=itmserv;
    }

    @GetMapping("/get")
    public List<Items> returnItems(){
        return itmserv.getAllItems();
    }

    @PostMapping("/add")
    public Items addItems(@RequestBody Items i){
        return itmserv.addItems(i);
    }

    @PatchMapping("/tag/{itemId}")
    public Items tagPeopleToItem(@PathVariable int itemId, @RequestBody TaggedPeople[] peopleArray){
        List<TaggedPeople> people = Arrays.asList(peopleArray);
        return itmserv.tagPeopleToItem(itemId, people);
    }

    @PatchMapping("/remove/{itemId}")
    public Items removeTaggedPeopleFromItem(@PathVariable int itemId, @RequestBody TaggedPeople person){
        return itmserv.removePeopleFromItem(itemId,person);
    }

    @DeleteMapping("/removeItem/{itemId}")
    public ResponseEntity<List<Items>> deleteItemFromItems(@PathVariable int itemId){
        List<Items> updatedList = itmserv.removeItem(itemId);
        return ResponseEntity.ok(updatedList);
    }


}
