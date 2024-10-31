package com.kappann.citel.usecases;

import com.kappann.citel.application.dtos.PersonDTO;
import com.kappann.citel.application.dtos.response.OverWeightByGenderResponseDTO;

import java.util.List;

public interface OverWeightByGenderUseCase {
    List<OverWeightByGenderResponseDTO> calculateObesityPercentageByGender(List<PersonDTO> candidates);
}
