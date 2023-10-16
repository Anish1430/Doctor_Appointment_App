package com.Anish.DoctorAppontmentApp.controller;

import com.Anish.DoctorAppontmentApp.model.Doctor;
import com.Anish.DoctorAppontmentApp.model.Patient;
import com.Anish.DoctorAppontmentApp.model.Qualification;
import com.Anish.DoctorAppontmentApp.model.Specialization;
import com.Anish.DoctorAppontmentApp.model.dto.AuthenticationInputDto;
import com.Anish.DoctorAppontmentApp.model.dto.ScheduledAppointmentDTO;
import com.Anish.DoctorAppontmentApp.model.dto.SignInInputDto;
import com.Anish.DoctorAppontmentApp.service.AppointmentService;
import com.Anish.DoctorAppontmentApp.service.DoctorService;
import com.Anish.DoctorAppontmentApp.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class PatientController {
    @Autowired
    PatientService patientService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    DoctorService doctorService;

    //Sign Up Code ....

    @PostMapping("patient/signUp")
     public String patientSignUp(@Valid @RequestBody Patient patient) {
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

    //schedule an Appointment...

    @PostMapping("patient/appointment/schedule")
    public String scheduleAppointment(@RequestBody ScheduledAppointmentDTO scheduledAppointmentDTO){
        return  appointmentService.scheduleAppointment(scheduledAppointmentDTO.getAuthInfo(),scheduledAppointmentDTO.getAppointment());
    }

  //Cancel An Appointment...

    @DeleteMapping("patient/appointment/{appointmentId}/cancel")
    public String cancelAppointment(@RequestBody AuthenticationInputDto authInfo, @PathVariable Integer appointmentId) {
        return appointmentService.cancelAppointment(authInfo,appointmentId);
    }

    @GetMapping("doctors/qualification/{qual}/or/specialization/{spec}")

    public List<Doctor> getDoctorsByQualificationOrSpec(@RequestBody AuthenticationInputDto authInfo,@PathVariable Qualification qual, @PathVariable Specialization spec) {
      return doctorService.getDoctorsByQualificationOrSpec(authInfo,qual,spec);
    }
}
