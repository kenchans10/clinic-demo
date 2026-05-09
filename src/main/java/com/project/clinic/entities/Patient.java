package com.project.clinic.entities;

import com.project.clinic.configs.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "phone_no")
    private String phoneNo;
    private Boolean active;
}
