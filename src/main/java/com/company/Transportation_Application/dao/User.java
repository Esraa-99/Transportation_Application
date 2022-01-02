package com.company.Transportation_Application.dao;

import com.company.Transportation_Application.model.Person;
import com.company.Transportation_Application.model.Registration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public class User extends Person implements Registration {
    @NonNull
    public String Source;
    @NonNull
    public String Destination;

    @Override
    public String Register() {
        return null;
    }
    @GetMapping
    @RequestMapping("dao/v1/User")
    public int run(@JsonProperty("var") int var){
        return var;
    }
}
