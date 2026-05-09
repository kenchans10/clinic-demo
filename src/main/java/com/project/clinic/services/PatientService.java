package com.project.clinic.services;

import com.project.clinic.entities.Patient;
import com.project.clinic.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PatientService {
    public final PatientRepository patientRepository;
    public static final String CACHE_PATIENT_ALL = "patientsAll";

    @Cacheable(CACHE_PATIENT_ALL)
    public List<Patient> findAll(){
        log.info("DB Hit for findAll");
        return patientRepository.findAll();
    }
}
