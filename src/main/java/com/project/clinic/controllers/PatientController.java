package com.project.clinic.controllers;

import com.project.clinic.Specification.PatientSpecification;
import com.project.clinic.dtos.GridSortDTO;
import com.project.clinic.dtos.KendoGridRequestDTO;
import com.project.clinic.dtos.PatientDTO;
import com.project.clinic.entities.Appointment;
import com.project.clinic.entities.Doctor;
import com.project.clinic.entities.Patient;
import com.project.clinic.entities.TreatmentHistory;
import com.project.clinic.mappers.PatientMapper;
import com.project.clinic.repositories.AppointmentRepository;
import com.project.clinic.repositories.DoctorRepository;
import com.project.clinic.repositories.PatientRepository;
import com.project.clinic.repositories.TreatmentHistoryRepository;
import com.project.clinic.requests.ApiResponse;
import com.project.clinic.services.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientMapper patientMapper;
    private final PatientService patientService;
    private final PatientRepository patientRepository;

    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final TreatmentHistoryRepository treatmentHistoryRepository;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<PatientDTO>>> getPatient(){
        long start = System.currentTimeMillis();
        log.info("Request Patient List");
        List<Patient> patientList = patientService.findAll();
        long end = System.currentTimeMillis();
        log.info("API /all execution time: {} ms", (end - start));
        return ResponseEntity.ok(
                new ApiResponse<>((end - start),
                        patientMapper.toDTOList(patientList),
                        patientList.stream().count()
                )
        );
    }

    @PostMapping("/paginate")
    public ResponseEntity<ApiResponse<List<PatientDTO>>> getPaginate(@RequestBody KendoGridRequestDTO requestDTO){
        long start = System.currentTimeMillis();
        log.info("Request Patient Paginate");
        PageRequest pageable;

        // SORT
        if (requestDTO.getSort() != null
                && !requestDTO.getSort().isEmpty()) {

            GridSortDTO sortDTO =
                    requestDTO.getSort().get(0);

            Sort sort = sortDTO.getDir().equals("asc")
                    ? Sort.by(sortDTO.getField()).ascending()
                    : Sort.by(sortDTO.getField()).descending();

            pageable = PageRequest.of(
                    requestDTO.getPage() - 1,
                    requestDTO.getTake(),
                    sort
            );

        } else {

            pageable = PageRequest.of(
                    requestDTO.getPage() - 1,
                    requestDTO.getTake()
            );
        }

        Page<Patient> result;

        if (requestDTO.getFilter() != null) {

            Specification<Patient> specification =
                    PatientSpecification.build(
                            requestDTO.getFilter()
                    );

            result = patientRepository.findAll(
                    specification,
                    pageable
            );

        } else {

            result = patientRepository.findAll(pageable);
        }
        long end = System.currentTimeMillis();
        log.info("API /all execution time: {} ms", (end - start));
        return ResponseEntity.ok(
                new ApiResponse<>((end - start),
                        patientMapper.toDTOList(result.getContent()),
                        result.getTotalElements()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PatientDTO>> getEmployee(@PathVariable String id) {
        long start = System.currentTimeMillis();
        log.info("Request Patient [id={}]", id);
        Patient patient = patientService.findById(id);
        long end = System.currentTimeMillis();
        log.info("API /id execution time: {} ms", (end - start));
        return ResponseEntity.ok(
                new ApiResponse<>((end - start),
                        patientMapper.toDTO(patient),
                        null
                )
        );
    }

    @GetMapping("/generate-data")
    public ResponseEntity<String> generatePatient(){
        Faker faker = new Faker();
        String[] prefixes = {"010", "011", "012", "013", "014", "016", "017", "018", "019"};

        List<Doctor> doctors = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Doctor doctor = new Doctor();
            doctor.setName(faker.name().fullName());
            doctor.setPhoneNo(prefixes[faker.random().nextInt(prefixes.length)]
                    + faker.number().digits(7));
            doctor.setEmail(faker.internet().emailAddress());
            doctor.setActive(true);

            doctors.add(doctorRepository.save(doctor));
        }

        for (int i = 0; i < 10000; i++) {
            String prefix = prefixes[faker.random().nextInt(prefixes.length)];

            Patient p = new Patient();
            p.setName(faker.name().fullName());
            p.setIdType(faker.options().option("1", "3"));
            p.setIdNo(faker.number().digits(12));
            p.setDob(faker.date().birthday().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
            p.setBloodType("B+");
            p.setPhoneNo(prefix + faker.number().digits(7));
            p.setActive(true);

            Patient savedPatient = patientRepository.save(p);

            Doctor randomDoctor = doctors.get(faker.random().nextInt(doctors.size()));

            Appointment appointment = new Appointment();
            appointment.setPatient(savedPatient);
            appointment.setDoctor(randomDoctor);
            appointment.setAppointmentDate(LocalDateTime.now());
            appointment.setActive(true);

            appointmentRepository.save(appointment);

            int totalHistory = faker.number().numberBetween(1, 6);

            for (int j = 0; j < totalHistory; j++) {

                TreatmentHistory history = new TreatmentHistory();
                history.setPatient(savedPatient);
                history.setDoctor(randomDoctor);
                history.setVisitDate(
                        LocalDateTime.now()
                                .minusDays(faker.number().numberBetween(1, 365))
                );

                history.setDiagnosis(
                        faker.options().option(
                                "Fever",
                                "Headache",
                                "Diabetes",
                                "Hypertension",
                                "Flu",
                                "Cough",
                                "Back Pain"
                        )
                );

                history.setTreatmentNotes(
                        faker.lorem().paragraph()
                );

                history.setMedication(
                        faker.options().option(
                                "Paracetamol",
                                "Antibiotics",
                                "Ibuprofen",
                                "Cough Syrup",
                                "Vitamin C"
                        )
                );

                treatmentHistoryRepository.save(history);
            }
        }
        return ResponseEntity.ok("Generate Completed");
    }
}
