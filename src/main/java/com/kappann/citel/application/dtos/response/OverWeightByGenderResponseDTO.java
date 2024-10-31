package com.kappann.citel.application.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OverWeightByGenderResponseDTO {
    private String gender;
    private String percentage;
}