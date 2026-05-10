package com.project.clinic.repositories;

import com.project.clinic.entities.TreatmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentHistoryRepository extends JpaRepository<TreatmentHistory, Long> {
}
