package com.Anish.DoctorAppontmentApp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,scope = Doctor.class,property = "docId")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer docId;

    private String  docName;
    private double  docFee;

    @Enumerated(EnumType.STRING)
    private Specialization docSpecialization;

    @Enumerated(EnumType.STRING)
    private Qualification docQualification;

    private String docContact;

    @OneToMany(mappedBy = "doctor")
    List<Appointment> appointments;
}
