package com.project.clinic.repositories;

import com.project.clinic.entities.Patient;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {
    @EntityGraph(attributePaths = {"appointments", "appointments.doctor"})
    Optional<Patient> findPatientByIdNo(String idNo);
}
