package com.project.clinic.services;

import com.project.clinic.entities.Patient;
import com.project.clinic.exceptions.NotFoundException;
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
    public static final String CACHE_PATIENT_BY_ID = "patientsById";

    @Cacheable(CACHE_PATIENT_ALL)
    public List<Patient> findAll(){
        log.info("DB Hit for findAll");
        return patientRepository.findAll();
    }

    @Cacheable(cacheNames = CACHE_PATIENT_BY_ID, key = "#id")
    public Patient findById(String id) {
        log.info("DB hit for findById {}", id);
        return patientRepository.findPatientByIdNo(id)
                .orElseThrow(() -> new NotFoundException.EmployeeNotFoundException(id));
    }

    // Evict for write operations
//    @CacheEvict(cacheNames = {CACHE_EMPLOYEES_ALL, CACHE_EMPLOYEES_BY_ID}, allEntries = true)
//    public Employee create(Employee employee) {
//        return employeeRepository.save(employee);
//    }
//
//    @CacheEvict(cacheNames = {CACHE_EMPLOYEES_ALL, CACHE_EMPLOYEES_BY_ID}, allEntries = true)
//    public Employee upsert(Long id, Employee employeeEntity) {
//        return employeeRepository.findById(id)
//                .map(existing -> {
//                    existing.setFirstName(employeeEntity.getFirstName());
//                    existing.setLastName(employeeEntity.getLastName());
//                    existing.setRole(employeeEntity.getRole());
//                    return employeeRepository.save(existing);
//                })
//                .orElseGet(() -> {
//                    employeeEntity.setId(id);
//                    return employeeRepository.save(employeeEntity);
//                });
//    }
//
//    @CacheEvict(cacheNames = {CACHE_EMPLOYEES_ALL, CACHE_EMPLOYEES_BY_ID}, allEntries = true)
//    public void delete(Long id) {
//        if (!employeeRepository.existsById(id)) {
//            throw new EmployeeNotFoundException(id);
//        }
//        employeeRepository.deleteById(id);
//    }
}
