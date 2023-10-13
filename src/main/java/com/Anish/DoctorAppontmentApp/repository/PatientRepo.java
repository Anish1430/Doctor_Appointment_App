package com.Anish.DoctorAppontmentApp.repository;

import com.Anish.DoctorAppontmentApp.model.BloodGroup;
import com.Anish.DoctorAppontmentApp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient,Integer> {
    Patient findFirstByPatientEmail(String newEmail);

    List<Patient> findByPatientBloodGroup(BloodGroup bloodGroup);
}
