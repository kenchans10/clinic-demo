package com.project.clinic.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class PatientDTO {
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "ID Type is required")
    private String idType;

    @NotBlank(message = "ID No is required")
    private String idNo;

    private LocalDate dob;

    private String bloodType;

    private String phoneNo;

    private Boolean active;

    List<AppointmentDTO> appointments;

    List<TreatmentHistoryDTO> treatments;

}
