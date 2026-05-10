package com.project.clinic.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDTO {
    private LocalDateTime appointmentDate;
    private String doctorName;
}