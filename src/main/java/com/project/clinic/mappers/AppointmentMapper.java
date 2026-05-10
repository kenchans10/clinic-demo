package com.project.clinic.mappers;

import com.project.clinic.dtos.AppointmentDTO;
import com.project.clinic.entities.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PatientMapper.class})
public interface AppointmentMapper {

    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    Appointment toEntity(AppointmentDTO dto);

    @Mapping(source = "doctor.name", target = "doctorName")
    AppointmentDTO toDTO(Appointment appointment);
}