package com.project.clinic.mappers;

import com.project.clinic.dtos.AppointmentDTO;
import com.project.clinic.entities.Appointment;
import com.project.clinic.entities.Doctor;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-10T11:49:10+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 25.0.3 (Oracle Corporation)"
)
@Component
public class AppointmentMapperImpl implements AppointmentMapper {

    @Override
    public Appointment toEntity(AppointmentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Appointment appointment = new Appointment();

        appointment.setAppointmentDate( dto.getAppointmentDate() );

        return appointment;
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
}
