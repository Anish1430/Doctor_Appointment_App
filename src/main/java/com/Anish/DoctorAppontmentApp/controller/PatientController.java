package com.Anish.DoctorAppontmentApp.controller;

import com.Anish.DoctorAppontmentApp.model.Patient;
import com.Anish.DoctorAppontmentApp.model.dto.AuthenticationInputDto;
import com.Anish.DoctorAppontmentApp.model.dto.SignInInputDto;
import com.Anish.DoctorAppontmentApp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {
    @Autowired
    PatientService patientService;

    //Sign Up Code ....

    @PostMapping("patient/signUp")
     public String patientSignUp(@RequestBody Patient patient) {
        return patientService.patientSignUp(patient);
    }



    //Sign In code..........

    @PostMapping("patient/signIn")
    public String patientSignIn(@RequestBody SignInInputDto signInInput) {
       return patientService.patientSignIn(signInInput);
    }

    //Sign Out code.....

    @DeleteMapping("patient/signOut")
     public String patientSignOut(@RequestBody AuthenticationInputDto authInfo) {
        return patientService.patientSignOut(authInfo);
    }
}
