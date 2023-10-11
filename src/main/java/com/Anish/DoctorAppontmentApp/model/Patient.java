package com.Anish.DoctorAppontmentApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;
    private String patientName;
    private String patientEmail;
    private String patientPassword;

    @Enumerated(EnumType.STRING)
    private Gender patientGender;

    @Enumerated(EnumType.STRING)
    private BloodGroup patientBloodGroup;

    private String patientContact;
    private LocalDateTime patientDateOfBirth;
}
