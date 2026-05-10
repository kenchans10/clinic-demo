package com.project.clinic.Specification;

import com.project.clinic.dtos.FilterDescriptionDTO;
import com.project.clinic.dtos.GridFilterDTO;
import com.project.clinic.entities.Patient;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PatientSpecification {

    public static Specification<Patient> build(
            GridFilterDTO filterDTO
    ) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filterDTO != null
                    && filterDTO.getFilters() != null) {

                for (FilterDescriptionDTO filter
                        : filterDTO.getFilters()) {

                    String field = filter.getField();
                    String operator = filter.getOperator();
                    Object value = filter.getValue();

                    switch (operator) {

                        case "contains":
                            predicates.add(
                                    cb.like(
                                            cb.lower(root.get(field)),
                                            "%" + value.toString().toLowerCase() + "%"
                                    )
                            );
                            break;

                        case "eq":
                            Class<?> type = root.get(field).getJavaType();
                            Object convertedValue = convertValue(type, value);

                            predicates.add(
                                    cb.equal(root.get(field), convertedValue)
                            );
                            break;

                        case "neq":
                            predicates.add(
                                    cb.notEqual(
                                            root.get(field),
                                            value
                                    )
                            );
                            break;

                        case "startswith":
                            predicates.add(
                                    cb.like(
                                            cb.lower(root.get(field)),
                                            value.toString().toLowerCase() + "%"
                                    )
                            );
                            break;

                        case "endswith":
                            predicates.add(
                                    cb.like(
                                            cb.lower(root.get(field)),
                                            "%" + value.toString().toLowerCase()
                                    )
                            );
                            break;
                    }
                }
            }

            // LOGIC
            if ("or".equalsIgnoreCase(filterDTO.getLogic())) {

                return cb.or(
                        predicates.toArray(new Predicate[0])
                );
            }

            return cb.and(
                    predicates.toArray(new Predicate[0])
            );
        };
    }

    private static Object convertValue(Class<?> type, Object value) {

        if (value == null) return null;

        String str = value.toString();

        if (type.equals(java.time.LocalDate.class)) {

            // handle ISO datetime like 1971-07-03T16:30:00.000Z
            if (str.contains("T")) {
                return java.time.Instant
                        .parse(str)
                        .atZone(java.time.ZoneOffset.UTC)
                        .toLocalDate();
            }

            return java.time.LocalDate.parse(str);
        }

        if (type.equals(java.time.LocalDateTime.class)) {

            if (str.endsWith("Z")) {
                return java.time.Instant
                        .parse(str)
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDateTime();
            }

            return java.time.LocalDateTime.parse(str);
        }

        if (type.equals(Integer.class)) {
            return Integer.valueOf(str);
        }

        if (type.equals(Long.class)) {
            return Long.valueOf(str);
        }

        return value;
    }
}