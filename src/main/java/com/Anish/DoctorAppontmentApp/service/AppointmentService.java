package com.Anish.DoctorAppontmentApp.service;

import com.Anish.DoctorAppontmentApp.model.Appointment;
import com.Anish.DoctorAppontmentApp.model.Doctor;
import com.Anish.DoctorAppontmentApp.model.Patient;
import com.Anish.DoctorAppontmentApp.model.dto.AuthenticationInputDto;
import com.Anish.DoctorAppontmentApp.repository.AppointmentRepo;
import com.Anish.DoctorAppontmentApp.repository.DoctorRepo;
import com.Anish.DoctorAppontmentApp.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepo appointmentRepo;

    @Autowired
    PTokenService pTokenService;

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    DoctorRepo doctorRepo;

    public String scheduleAppointment(AuthenticationInputDto authInfo, Appointment appointment) {
        if (pTokenService.authenticate(authInfo)) {
           //Find the patient.....

            String email=authInfo.getEmail();
            Patient patient=patientRepo.findFirstByPatientEmail(email);
              appointment.setPatient(patient);

            //Find the Doctor....

            Integer docId=appointment.getDoctor().getDocId();

             Doctor doc =doctorRepo.findById(docId).orElseThrow();

             appointment.setDoctor(doc);

            if(doc != null) {
                appointment.setAppCreationTime(LocalDateTime.now());
                appointmentRepo.save(appointment);
                return "Appointment Booked at time :" + appointment.getAppScheduleTime() + "with Dr. "+ doc.getDocName();
            }else  {
                return "Doctor does not exist, Could not Book Appointment !!!";
            }

        }else {
            return "Un Authenticated Access !!!";
        }
    }

    //Cancel Appointment Code......

    public String cancelAppointment(AuthenticationInputDto authInfo, Integer appointmentId) {

        if (pTokenService.authenticate(authInfo)) {
            String email=authInfo.getEmail();
            Patient patient=patientRepo.findFirstByPatientEmail(email);
            Appointment existingAppointment=appointmentRepo.findById(appointmentId).orElseThrow();

            if (existingAppointment.getPatient().equals(patient)) {
                  appointmentRepo.deleteById(appointmentId);
                  return "Appointment with " + existingAppointment.getDoctor().getDocName()+" has been cancelled";
            }else {
                 return "Cancel Appointment";
            }
        }else {
            return "Un Authenticated Access !!!";
        }
    }
}
