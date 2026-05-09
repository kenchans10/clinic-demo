package com.project.clinic.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PatientDTO {
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "ID Type is required")
    private String idType;

    @NotBlank(message = "ID No is required")
    private String idNo;

    private String phoneNo;

    private Boolean active;
}
