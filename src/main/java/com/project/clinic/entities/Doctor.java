package com.project.clinic.entities;

import com.project.clinic.configs.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "doctor")
public class Doctor extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "phone_no")
    private String phoneNo;
    private String email;
    private Boolean active;
}
