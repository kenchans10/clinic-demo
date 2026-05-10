package com.project.clinic.mappers;

import com.project.clinic.dtos.AppointmentDTO;
import com.project.clinic.dtos.PatientDTO;
import com.project.clinic.dtos.PatientDetailDTO;
import com.project.clinic.dtos.TreatmentHistoryDTO;
import com.project.clinic.entities.Appointment;
import com.project.clinic.entities.Doctor;
import com.project.clinic.entities.Patient;
import com.project.clinic.entities.TreatmentHistory;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-10T23:26:40+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25.0.3 (Oracle Corporation)"
)
@Component
public class PatientMapperImpl implements PatientMapper {

    @Override
    public PatientDTO toPatientDTO(Patient patient) {
        if ( patient == null ) {
            return null;
        }

        PatientDTO patientDTO = new PatientDTO();

        patientDTO.setId( patient.getId() );
        patientDTO.setName( patient.getName() );
        patientDTO.setIdType( patient.getIdType() );
        patientDTO.setIdNo( patient.getIdNo() );
        patientDTO.setDob( patient.getDob() );
        patientDTO.setBloodType( patient.getBloodType() );
        patientDTO.setPhoneNo( patient.getPhoneNo() );
        patientDTO.setActive( patient.getActive() );

        return patientDTO;
    }

    @Override
    public List<PatientDTO> toPatientDTOList(List<Patient> patient) {
        if ( patient == null ) {
            return null;
        }

        List<PatientDTO> list = new ArrayList<PatientDTO>( patient.size() );
        for ( Patient patient1 : patient ) {
            list.add( toPatientDTO( patient1 ) );
        }

        return list;
    }

    @Override
    public PatientDetailDTO toDTO(Patient patient) {
        if ( patient == null ) {
            return null;
        }

        PatientDetailDTO patientDetailDTO = new PatientDetailDTO();

        patientDetailDTO.setId( patient.getId() );
        patientDetailDTO.setName( patient.getName() );
        patientDetailDTO.setIdType( patient.getIdType() );
        patientDetailDTO.setIdNo( patient.getIdNo() );
        patientDetailDTO.setDob( patient.getDob() );
        patientDetailDTO.setBloodType( patient.getBloodType() );
        patientDetailDTO.setPhoneNo( patient.getPhoneNo() );
        patientDetailDTO.setActive( patient.getActive() );
        patientDetailDTO.setAppointments( appointmentListToAppointmentDTOList( patient.getAppointments() ) );
        patientDetailDTO.setTreatments( treatmentHistoryListToTreatmentHistoryDTOList( patient.getTreatments() ) );

        return patientDetailDTO;
    }

    @Override
    public List<PatientDetailDTO> toDTOList(List<Patient> patientList) {
        if ( patientList == null ) {
            return null;
        }

        List<PatientDetailDTO> list = new ArrayList<PatientDetailDTO>( patientList.size() );
        for ( Patient patient : patientList ) {
            list.add( toDTO( patient ) );
        }

        return list;
    }

    @Override
    public AppointmentDTO toDTO(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }

        AppointmentDTO appointmentDTO = new AppointmentDTO();

        appointmentDTO.setDoctorName( appointmentDoctorName( appointment ) );
        appointmentDTO.setAppointmentDate( appointment.getAppointmentDate() );

        return appointmentDTO;
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

    protected List<AppointmentDTO> appointmentListToAppointmentDTOList(List<Appointment> list) {
        if ( list == null ) {
            return null;
        }

        List<AppointmentDTO> list1 = new ArrayList<AppointmentDTO>( list.size() );
        for ( Appointment appointment : list ) {
            list1.add( toDTO( appointment ) );
        }

        return list1;
    }

    protected List<TreatmentHistoryDTO> treatmentHistoryListToTreatmentHistoryDTOList(List<TreatmentHistory> list) {
        if ( list == null ) {
            return null;
        }

        List<TreatmentHistoryDTO> list1 = new ArrayList<TreatmentHistoryDTO>( list.size() );
        for ( TreatmentHistory treatmentHistory : list ) {
            list1.add( toDTO( treatmentHistory ) );
        }

        return list1;
    }

    private String appointmentDoctorName(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }
        Doctor doctor = appointment.getDoctor();
        if ( doctor == null ) {
            return null;
        }
        String name = doctor.getName();
        if ( name == null ) {
            return null;
        }
        return name;
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
