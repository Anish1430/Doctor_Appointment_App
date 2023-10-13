package com.Anish.DoctorAppontmentApp.controller;

import com.Anish.DoctorAppontmentApp.model.BloodGroup;
import com.Anish.DoctorAppontmentApp.model.Doctor;
import com.Anish.DoctorAppontmentApp.model.Patient;
import com.Anish.DoctorAppontmentApp.model.dto.AuthenticationInputDto;
import com.Anish.DoctorAppontmentApp.service.DoctorService;
import com.Anish.DoctorAppontmentApp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class AdminController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;

    @PostMapping("doctor")
     public String addDoctor(@RequestBody Doctor newDoctor) {
        return doctorService.addDoctor(newDoctor);
    }


    @GetMapping("patients")
    public List<Patient> getAllPatients() {
        return patientService.getAllpatients();
    }

    @GetMapping("patients/bloodGroup/{bloodGroup}")
    public List<Patient> getAllPatientsByBloodGroup(@PathVariable BloodGroup bloodGroup) {
        return patientService.getAllPatientsByBloodGroup(bloodGroup);
    }
}
