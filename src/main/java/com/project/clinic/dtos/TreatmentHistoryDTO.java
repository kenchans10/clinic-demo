package com.project.clinic.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TreatmentHistoryDTO {
    private String doctorName;
    private LocalDateTime visitDate;
    private String diagnosis;
    private String treatmentNotes;
    private String medication;
}
