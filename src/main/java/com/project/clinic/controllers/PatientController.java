package com.project.clinic.controllers;

import com.project.clinic.dtos.PatientDTO;
import com.project.clinic.entities.Patient;
import com.project.clinic.mappers.PatientMapper;
import com.project.clinic.repositories.PatientRepository;
import com.project.clinic.services.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientMapper patientMapper;
    private final PatientService patientService;
    private final PatientRepository patientRepository;

    @GetMapping("/all")
    public ResponseEntity<List<PatientDTO>> getPatient(){
        long start = System.currentTimeMillis();
        log.info("Request Patient List");
        List<Patient> patientList = patientService.findAll();
        long end = System.currentTimeMillis();
        log.info("API /all execution time: {} ms", (end - start));
        return ResponseEntity.ok(patientMapper.toDTOList(patientList));
    }

    @GetMapping("/generate-data")
    public ResponseEntity<String> generatePatient(){
        Faker faker = new Faker();
        for (int i = 0; i < 10000; i++) {
            Patient p = new Patient();
            p.setName(faker.name().fullName());
            p.setIdType(faker.options().option("1", "3"));
            p.setIdNo(faker.number().digits(12));
            p.setPhoneNo(faker.phoneNumber().phoneNumber());
            p.setActive(true);

            patientRepository.save(p);
        }
        return ResponseEntity.ok("Generate Completed");
    }
}
