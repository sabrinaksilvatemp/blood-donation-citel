package com.kappann.citel.application.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AverageBMIResponseDTO {
    private Double averageBmi;
    private String ageGroup;

}
