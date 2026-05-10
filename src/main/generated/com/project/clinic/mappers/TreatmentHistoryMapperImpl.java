package com.project.clinic.mappers;

import com.project.clinic.dtos.TreatmentHistoryDTO;
import com.project.clinic.entities.Doctor;
import com.project.clinic.entities.TreatmentHistory;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-10T11:49:10+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25.0.3 (Oracle Corporation)"
)
@Component
public class TreatmentHistoryMapperImpl implements TreatmentHistoryMapper {

    @Override
    public TreatmentHistory toEntity(TreatmentHistoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        TreatmentHistory treatmentHistory = new TreatmentHistory();

        treatmentHistory.setVisitDate( dto.getVisitDate() );
        treatmentHistory.setDiagnosis( dto.getDiagnosis() );
        treatmentHistory.setTreatmentNotes( dto.getTreatmentNotes() );
        treatmentHistory.setMedication( dto.getMedication() );

        return treatmentHistory;
    }

    @Override
    public TreatmentHistoryDTO toDTO(TreatmentHistory treatmentHistory) {
        if ( treatmentHistory == null ) {
            return null;
        }

        TreatmentHistoryDTO treatmentHistoryDTO = new TreatmentHistoryDTO();

        treatmentHistoryDTO.setDoctorName( treatmentHistoryDoctorName( treatmentHistory ) );
        treatmentHistoryDTO.setVisitDate( treatmentHistory.getVisitDate() );
        treatmentHistoryDTO.setDiagnosis( treatmentHistory.getDiagnosis() );
        treatmentHistoryDTO.setTreatmentNotes( treatmentHistory.getTreatmentNotes() );
        treatmentHistoryDTO.setMedication( treatmentHistory.getMedication() );

        return treatmentHistoryDTO;
    }

    private String treatmentHistoryDoctorName(TreatmentHistory treatmentHistory) {
        if ( treatmentHistory == null ) {
            return null;
        }
        Doctor doctor = treatmentHistory.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        String name = doctor.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
