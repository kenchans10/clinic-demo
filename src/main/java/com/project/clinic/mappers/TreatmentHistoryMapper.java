package com.project.clinic.mappers;

import com.project.clinic.dtos.TreatmentHistoryDTO;
import com.project.clinic.entities.TreatmentHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PatientMapper.class})
public interface TreatmentHistoryMapper {

    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    TreatmentHistory toEntity(TreatmentHistoryDTO dto);

    @Mapping(source = "doctor.name", target = "doctorName")
    TreatmentHistoryDTO toDTO(TreatmentHistory treatmentHistory);
}
