package com.kappann.citel.application.services;

import com.kappann.citel.application.dtos.request.PersonRequestDTO;
import com.kappann.citel.application.dtos.response.ResponseDTO;

import java.util.List;

public interface BloodDonationDataProcessorService {
    ResponseDTO processDonationCandidatesData(List<PersonRequestDTO> candidatesRequest);
}
