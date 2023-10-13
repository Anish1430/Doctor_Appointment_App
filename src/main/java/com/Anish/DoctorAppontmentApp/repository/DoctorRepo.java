package com.Anish.DoctorAppontmentApp.repository;

import com.Anish.DoctorAppontmentApp.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor,Integer> {
}
