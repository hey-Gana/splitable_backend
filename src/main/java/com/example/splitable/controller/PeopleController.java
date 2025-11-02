package com.example.splitable.controller;

import com.example.splitable.model.People;
import com.example.splitable.services.PeopleServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {
    private final PeopleServices pplserv;

    //constructor injection
    public PeopleController(PeopleServices pplserv){
        this.pplserv=pplserv;
    }

    @GetMapping("/get")
    public List<People> returnPeople(){
        return pplserv.getAllPeople();
    }

    @PostMapping("/add")
    public People addPeople(@RequestBody People p){
        return pplserv.addPeople(p);
    }
}
