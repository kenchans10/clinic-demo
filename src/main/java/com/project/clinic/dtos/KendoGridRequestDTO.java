package com.project.clinic.dtos;

import lombok.Data;

import java.util.List;

@Data
public class KendoGridRequestDTO {
    private Integer skip;
    private Integer take;
    private Integer page;
    private List<GridSortDTO> sort;
    private GridFilterDTO filter;
}
