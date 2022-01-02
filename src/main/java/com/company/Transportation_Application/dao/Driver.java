package com.company.Transportation_Application.dao;

import com.company.Transportation_Application.model.Person;
import com.company.Transportation_Application.model.Registration;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository("driver")
public class Driver extends Person implements Registration {
    @NonNull
    public String National_ID;
    public String Driving_license;

    @Override
    public String Register() {
        return null;
    }
}
