package com.example.splitable.services;

import com.example.splitable.model.People;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleServices {
    private final List<People> ListOfPeople = new ArrayList<>();

    public People addPeople(People p){
        ListOfPeople.add(p);
        return p;
    }

    public List<People> getAllPeople(){
        return new ArrayList<>(ListOfPeople);
    }


}
