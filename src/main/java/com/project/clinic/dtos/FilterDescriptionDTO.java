package com.project.clinic.dtos;

import lombok.Data;

@Data
public class FilterDescriptionDTO {
    private String field;
    private String operator;
    private Object value;
}
