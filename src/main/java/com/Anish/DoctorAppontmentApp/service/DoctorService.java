package com.Anish.DoctorAppontmentApp.service;

import com.Anish.DoctorAppontmentApp.model.Doctor;
import com.Anish.DoctorAppontmentApp.model.Patient;
import com.Anish.DoctorAppontmentApp.model.Qualification;
import com.Anish.DoctorAppontmentApp.model.Specialization;
import com.Anish.DoctorAppontmentApp.model.dto.AuthenticationInputDto;
import com.Anish.DoctorAppontmentApp.repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    @Autowired
    DoctorRepo doctorRepo;

    @Autowired
    PTokenService pTokenService;


    public List<Doctor> getAllDoctors(AuthenticationInputDto authInfo) {
        if (pTokenService.authenticate(authInfo)) {
            return doctorRepo.findAll();
        } else {
            return null;
        }
    }

    public String addDoctor(Doctor newDoctor) {
        Integer docId = newDoctor.getDocId();

        if (docId != null && doctorRepo.existsById(docId)) {
            return "Doctor Already exist";
        }
        newDoctor.setAppointments(null); //Linking anyway does not happen from fk side
        doctorRepo.save(newDoctor);
        return "Doctor Added !!!";
    }

    public Doctor getDoctorById(Integer id) {

        return doctorRepo.findById(id).orElseThrow();
    }

    public List<Doctor> getDoctorsByQualificationOrSpec(AuthenticationInputDto authInfo, Qualification qual, Specialization spec) {
        if (pTokenService.authenticate(authInfo)) {

            List<Doctor> doctors = doctorRepo.findByDocQualificationOrDocSpecialization(qual, spec);

            return doctors.stream().map(doc -> {
                        doc.setAppointments(null);
                        return doc;
                    })

                    .collect(Collectors.toList());
        }  else  {
            return null;
        }

    }
}
