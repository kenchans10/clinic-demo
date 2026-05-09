package com.project.clinic.mappers;

import com.project.clinic.dtos.PatientDTO;
import com.project.clinic.entities.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PatientMapper {

    PatientDTO toDTO(Patient patient);
    Patient toEntity(PatientDTO patientDTO);
    List<PatientDTO> toDTOList(List<Patient> patientList);
    List<Patient> toEntityList(List<PatientDTO> dtos);
}
