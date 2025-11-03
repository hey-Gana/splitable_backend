package com.example.splitable.controller;

import com.example.splitable.model.Items;
import com.example.splitable.services.ItemServices;
import org.springframework.web.bind.annotation.*;

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
}
