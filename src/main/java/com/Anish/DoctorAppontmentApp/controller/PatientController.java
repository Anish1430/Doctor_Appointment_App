package com.Anish.DoctorAppontmentApp.controller;

import com.Anish.DoctorAppontmentApp.model.Patient;
import com.Anish.DoctorAppontmentApp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {
    @Autowired
    PatientService patientService;

    //Sign Up Code ....

    @PostMapping("patient/signup")
     public String patientSignUp(Patient patient) {
        return patientService.patientSignUp(patient);
    }



    //Sign In code..........





    //Sign Out code.....
}
