package com.kappann.citel.usecases;

import com.kappann.citel.application.dtos.PersonDTO;
import com.kappann.citel.application.dtos.response.AverageBMIResponseDTO;

import java.util.List;

public interface AverageBmiByAgeGroupUseCase {
    List<AverageBMIResponseDTO> calculateAverageBmiByAgeGroup(List<PersonDTO> candidates);
}
