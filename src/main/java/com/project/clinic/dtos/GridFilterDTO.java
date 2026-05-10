package com.project.clinic.dtos;

import lombok.Data;

import java.util.List;

@Data
public class GridFilterDTO {
    private String logic;
    private List<FilterDescriptionDTO> filters;
}
