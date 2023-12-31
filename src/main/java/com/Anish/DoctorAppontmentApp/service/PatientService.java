package com.Anish.DoctorAppontmentApp.service;

import com.Anish.DoctorAppontmentApp.model.BloodGroup;
import com.Anish.DoctorAppontmentApp.model.Doctor;
import com.Anish.DoctorAppontmentApp.model.Patient;
import com.Anish.DoctorAppontmentApp.model.PatientAuthenticationToken;
import com.Anish.DoctorAppontmentApp.model.dto.AuthenticationInputDto;
import com.Anish.DoctorAppontmentApp.model.dto.SignInInputDto;
import com.Anish.DoctorAppontmentApp.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    PTokenService pTokenService;

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

            //patient table --> save patient...
            patient.setPatientPassword(encryptedPassword);
             patientRepo.save(patient);
             return "Patient registered";

        } catch (NoSuchAlgorithmException e) {
           return  "Internal Server Issue while Saving Password !! ";
        }

    }


    public String patientSignIn(SignInInputDto signInInput) {
       String email=signInInput.getEmail();
       Patient existingPatient=patientRepo.findFirstByPatientEmail(email);

         if (existingPatient == null){
             return "Not a valid email, Please Sign up first !!!";
         }
  //Password should be  matched or not.........
         String password=signInInput.getPassword();
        try {
            String encryptedPassword=PasswordEncryptor.encryptor(password);

            if (existingPatient.getPatientPassword().equals(encryptedPassword)) {

                //return a token for this Sign In..

                PatientAuthenticationToken token=new PatientAuthenticationToken(existingPatient);

               if (EmailService.sendMail(email,"Otp after login",token.getTokenValue())) {
                   pTokenService.createToken(token);
                   return "check Email for otp/token";
               }else  {
                   return "Error while generating Token !!!";
               }

            }else {
                //Password was wrong

                return "Invalid Credentials !!!";
            }

            //patient table --> save patient...

        } catch (NoSuchAlgorithmException e) {
            return  "Internal Server Issue while Saving Password, try again later !! ";
        }

    }

    public String patientSignOut(AuthenticationInputDto authInfo) {
        if (pTokenService.authenticate(authInfo)) {
            String tokenValue = authInfo.getTokenValue();
            pTokenService.deleteToken(tokenValue);
            return "Sign Out successfully !!!";
        }else {
            return "un Authentication Access !!!";
        }
    }

    public List<Patient> getAllpatients() {
          return patientRepo.findAll();
    }

    public List<Patient> getAllPatientsByBloodGroup(BloodGroup bloodGroup) {
        return patientRepo.findByPatientBloodGroup(bloodGroup);
    }
}
