package com.Anish.DoctorAppontmentApp.service;

import com.Anish.DoctorAppontmentApp.model.Doctor;
import com.Anish.DoctorAppontmentApp.model.dto.AuthenticationInputDto;
import com.Anish.DoctorAppontmentApp.repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    DoctorRepo doctorRepo;

    @Autowired
    PTokenService pTokenService;


    public List<Doctor> getAllDoctors(AuthenticationInputDto authInfo) {
        if (pTokenService.authenticate(authInfo)) {
            return doctorRepo.findAll();
        }else {
            return null;
        }
    }

    public String addDoctor(Doctor newDoctor) {
        Integer docId= newDoctor.getDocId();

        if(docId != null  && doctorRepo.existsById(docId)) {
            return "Doctor Already exist";
        }
        newDoctor.setAppointments(null); //Linking anyway does not happen from fk side
        doctorRepo.save(newDoctor);
        return "Doctor Added !!!";
    }

    public Doctor getDoctorById(Integer id) {

      return doctorRepo.findById(id).orElseThrow();
    }
}
