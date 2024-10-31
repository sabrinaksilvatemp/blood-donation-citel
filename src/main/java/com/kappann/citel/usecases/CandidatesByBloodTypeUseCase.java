package com.kappann.citel.usecases;

import com.kappann.citel.application.dtos.PersonDTO;
import com.kappann.citel.application.dtos.response.CandidatesByBloodTypeResponseDTO;

import java.util.List;

public interface CandidatesByBloodTypeUseCase {
    List<CandidatesByBloodTypeResponseDTO> getTotalEligibleForDonationByBloodType(List<PersonDTO> candidates);
}
