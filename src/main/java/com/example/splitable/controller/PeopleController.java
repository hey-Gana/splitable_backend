package com.example.splitable.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeopleController {
    @GetMapping("/people")
    public String returnPeople(){
        return "People";
    }
}
