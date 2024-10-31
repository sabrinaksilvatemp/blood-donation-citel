package com.kappann.citel.usecases;

import com.kappann.citel.application.dtos.PersonDTO;
import com.kappann.citel.application.dtos.response.AgeAverageByBloodTypeResponseDTO;

import java.util.List;

public interface AgeAverageByBloodTypeUseCase {
    List<AgeAverageByBloodTypeResponseDTO> calculateAverageAgeByBloodType(List<PersonDTO> candidates);
}
