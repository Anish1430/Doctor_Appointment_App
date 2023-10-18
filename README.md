# Doctor Appointment Application

This is a Spring Boot application for managing doctor appointments and patient records.

## Table of Contents
- [Introduction](#introduction)
- [Controllers](#controllers)
- [Models](#models)
- [Services](#services)
- [Repositories](#repositories)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)

## Introduction

This application provides functionalities for managing doctor appointments and patient records.

## Controllers

### AdminController

- `POST /doctor`: Add a new doctor.
- `GET /patients`: Retrieve a list of all patients.
- `GET /patients/bloodGroup/{bloodGroup}`: Retrieve a list of patients by blood group.

### DoctorController

- `GET /doctors`: Retrieve a list of all doctors.
- `GET /doctors/{id}`: Retrieve a doctor by ID.

### PatientController

- `POST /patient/signUp`: Sign up a patient.
- `POST /patient/signIn`: Sign in a patient.
- `DELETE /patient/signOut`: Sign out a patient.
- `POST /patient/appointment/schedule`: Schedule an appointment.
- `DELETE /patient/appointment/{appointmentId}/cancel`: Cancel an appointment.
- `GET /doctors/qualification/{qual}/or/specialization/{spec}`: Retrieve doctors by qualification or specialization.

## Models

### Admin

- `adminId`: Integer
- `adminName`: String
- `adminEmail`: String (valid email)
- `adminPassword`: String

### Appointment

- `appointmentId`: Integer
- `appointmentDesc`: String
- `appCreationTime`: LocalDateTime
- `appScheduleTime`: LocalDateTime
- `patient`: Patient
- `doctor`: Doctor

### BloodGroup

- Enum values: A+, A-, B+, B-, O+, O-, AB+, AB-

### Doctor

- `docId`: Integer
- `docName`: String
- `docFee`: double
- `docSpecialization`: Specialization
- `docQualification`: Qualification
- `docContact`: String
- `appointments`: List of Appointment

### Gender

- Enum values: MALE, FEMALE, TRANS

### Patient

- `patientId`: Integer
- `patientName`: String
- `patientEmail`: String (valid email)
- `patientPassword`: String
- `patientGender`: Gender
- `patientBloodGroup`: BloodGroup
- `patientContact`: String (10 digits)
- `patientDateOfBirth`: LocalDateTime
- `appointments`: List of Appointment

### PatientAuthenticationToken

- `tokenId`: Integer
- `tokenValue`: String
- `tokenCreationTime`: LocalDateTime
- `patient`: Patient

### Qualification

- Enum values: MBBS, MD, PGDMA

### SignInInputDto

- `email`: String (valid email)
- `password`: String (at least 8 characters with letters and numbers)

### SignUpInputDto

- `email`: String (valid email)
- `tokenValue`: String

### Specialization

- Enum values: ENT, GYNO, ORTHO, CARDIO, DENTAL

## Services

### AppointmentService

- `scheduleAppointment`: Schedule an appointment.
- `cancelAppointment`: Cancel an appointment.

### DoctorService

- `getAllDoctors`: Retrieve a list of all doctors.
- `addDoctor`: Add a new doctor.
- `getDoctorById`: Retrieve a doctor by ID.
- `getDoctorsByQualificationOrSpec`: Retrieve doctors by qualification or specialization.

### EmailService

- `sendMail`: Send an email.

### PTokenService

- `createToken`: Create a token.
- `deleteToken`: Delete a token.
- `authenticate`: Authenticate a user.

### PasswordEncryptor

- `encryptor`: Encrypt a password.

### PatientService

- `patientSignUp`: Sign up a patient.
- `patientSignIn`: Sign in a patient.
- `patientSignOut`: Sign out a patient.
- `getAllpatients`: Retrieve a list of all patients.
- `getAllPatientsByBloodGroup`: Retrieve a list of patients by blood group.

## Repositories

- `AppointmentRepo`: Repository for appointments.
- `DoctorRepo`: Repository for doctors.
- `IPTokenRepo`: Repository for patient authentication tokens.
- `PatientRepo`: Repository for patients.

## Configuration

- Email username and password for sending notifications.

## Running the Application

1. Build and run the application.
2. Access the API using appropriate endpoints.

