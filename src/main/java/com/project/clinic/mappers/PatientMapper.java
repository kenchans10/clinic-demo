package com.project.clinic.mappers;

import com.project.clinic.dtos.AppointmentDTO;
import com.project.clinic.dtos.PatientDTO;
import com.project.clinic.dtos.PatientDetailDTO;
import com.project.clinic.dtos.TreatmentHistoryDTO;
import com.project.clinic.entities.Appointment;
import com.project.clinic.entities.Patient;
import com.project.clinic.entities.TreatmentHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PatientMapper {

    PatientDTO toPatientDTO(Patient patient);
    List<PatientDTO> toPatientDTOList(List<Patient> patient);


    PatientDetailDTO toDTO(Patient patient);
    //Patient toEntity(PatientDTO patientDTO);
    List<PatientDetailDTO> toDTOList(List<Patient> patientList);
    //List<Patient> toEntityList(List<PatientDTO> dtos);

    @Mapping(source = "doctor.name", target = "doctorName")
    AppointmentDTO toDTO(Appointment appointment);

    @Mapping(source = "doctor.name", target = "doctorName")
    TreatmentHistoryDTO toDTO(TreatmentHistory treatmentHistory);
    //Appointment toEntity(AppointmentDTO appointmentDTO);
}
