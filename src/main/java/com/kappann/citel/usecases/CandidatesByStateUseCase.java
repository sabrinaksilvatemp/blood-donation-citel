package com.kappann.citel.usecases;

import com.kappann.citel.application.dtos.PersonDTO;
import com.kappann.citel.application.dtos.response.TotalByStateResponseDTO;

import java.util.List;

public interface CandidatesByStateUseCase {
    List<TotalByStateResponseDTO> getTotalByState(List<PersonDTO> candidates);
}
