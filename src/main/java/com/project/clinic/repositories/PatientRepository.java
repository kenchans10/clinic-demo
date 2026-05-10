package com.project.clinic.repositories;

import com.project.clinic.entities.Patient;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @EntityGraph(attributePaths = {"appointments", "appointments.doctor"})
    Optional<Patient> findPatientByIdNo(String idNo);
}
