package com.project.clinic.entities;

import com.project.clinic.configs.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "id_type")
    private String idType;
    @Column(name = "id_no")
    private String idNo;
    @Column(name = "date_of_birth")
    private LocalDate dob;
    @Column(name = "blood_type")
    private String bloodType;
    @Column(name = "phone_no")
    private String phoneNo;
    private Boolean active;

    @ToString.Exclude
    @OrderBy("appointmentDate DESC")
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    @ToString.Exclude
    @OrderBy("visitDate DESC")
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<TreatmentHistory> treatments;
}
