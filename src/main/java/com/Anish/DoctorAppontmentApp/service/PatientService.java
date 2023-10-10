package com.Anish.DoctorAppontmentApp.service;

import com.Anish.DoctorAppontmentApp.model.Patient;
import com.Anish.DoctorAppontmentApp.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class PatientService {

    @Autowired
    PatientRepo patientRepo;

    public String patientSignUp(Patient patient) {
        //check if already exist --> Not Allowed :try Logging In..


        String newEmail=patient.getPatientEmail();
         Patient existingPatient = patientRepo.findFirstByPatientEmail(newEmail);
          if(existingPatient != null) {
              return "email already in use";
          }


        //password are encrypted before we stored in the table

        String signUpPassword=patient.getPatientPassword();
        try {
            String encryptedPassword=PasswordEncryptor.encryptor(signUpPassword);

            patient.setPatientPassword(encryptedPassword);
             patientRepo.save(patient);
             return "Patient registered";

        } catch (NoSuchAlgorithmException e) {
           return  "Internal Server Issue while Saving Password !! ";
        }


        //patient table --> save patient...
    }
}
