package com.Anish.DoctorAppontmentApp.repository;

import com.Anish.DoctorAppontmentApp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient,Integer> {
    Patient findFirstByPatientEmail(String newEmail);
}
