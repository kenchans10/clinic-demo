package com.project.clinic.mappers;

import com.project.clinic.dtos.PatientDTO;
import com.project.clinic.entities.Patient;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-09T11:18:56+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25.0.3 (Oracle Corporation)"
)
@Component
public class PatientMapperImpl implements PatientMapper {

    @Override
    public PatientDTO toDTO(Patient patient) {
        if ( patient == null ) {
            return null;
        }

        PatientDTO patientDTO = new PatientDTO();

        patientDTO.setId( patient.getId() );
        patientDTO.setName( patient.getName() );
        patientDTO.setIdType( patient.getIdType() );
        patientDTO.setIdNo( patient.getIdNo() );
        patientDTO.setPhoneNo( patient.getPhoneNo() );
        patientDTO.setActive( patient.getActive() );

        return patientDTO;
    }

    @Override
    public Patient toEntity(PatientDTO patientDTO) {
        if ( patientDTO == null ) {
            return null;
        }

        Patient patient = new Patient();

        patient.setId( patientDTO.getId() );
        patient.setName( patientDTO.getName() );
        patient.setIdType( patientDTO.getIdType() );
        patient.setIdNo( patientDTO.getIdNo() );
        patient.setPhoneNo( patientDTO.getPhoneNo() );
        patient.setActive( patientDTO.getActive() );

        return patient;
    }

    @Override
    public List<PatientDTO> toDTOList(List<Patient> patientList) {
        if ( patientList == null ) {
            return null;
        }

        List<PatientDTO> list = new ArrayList<PatientDTO>( patientList.size() );
        for ( Patient patient : patientList ) {
            list.add( toDTO( patient ) );
        }

        return list;
    }

    @Override
    public List<Patient> toEntityList(List<PatientDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Patient> list = new ArrayList<Patient>( dtos.size() );
        for ( PatientDTO patientDTO : dtos ) {
            list.add( toEntity( patientDTO ) );
        }

        return list;
    }
}
