package com.Anish.DoctorAppontmentApp.repository;

import com.Anish.DoctorAppontmentApp.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {
}
