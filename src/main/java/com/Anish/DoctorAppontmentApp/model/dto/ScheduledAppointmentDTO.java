package com.Anish.DoctorAppontmentApp.model.dto;

import com.Anish.DoctorAppontmentApp.model.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduledAppointmentDTO {

    AuthenticationInputDto authInfo;
    Appointment appointment;
}
