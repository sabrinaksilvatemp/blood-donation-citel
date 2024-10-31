package com.kappann.citel.usecases.impl;

import com.kappann.citel.adapters.repositories.BloodTypeCompatibilityRepository;
import com.kappann.citel.application.dtos.PersonDTO;
import com.kappann.citel.application.dtos.response.CandidatesByBloodTypeResponseDTO;
import com.kappann.citel.domain.enums.BloodType;
import com.kappann.citel.usecases.CandidatesByBloodTypeUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CandidatesByBloodTypeUseCaseImpl implements CandidatesByBloodTypeUseCase {
    private BloodTypeCompatibilityRepository bloodTypeCompatibilityRepository;

    @Override
    public List<CandidatesByBloodTypeResponseDTO> getTotalEligibleForDonationByBloodType(List<PersonDTO> candidates) {
        Map<BloodType, Long> candidatesByBloodType = candidates.stream()
                .collect(Collectors.groupingBy(PersonDTO::getBloodType, Collectors.counting()));

        return candidatesByBloodType.keySet().stream()
                .map(bloodType -> {
                    List<BloodType> eligibleDonors = bloodTypeCompatibilityRepository.findEligibleDonorsForRecipient(bloodType);

                    Double totalEligibleForDonation = candidates.stream()
                            .filter(candidate -> eligibleDonors.contains(candidate.getBloodType()))
                            .count() * 1.0;

                    return CandidatesByBloodTypeResponseDTO.builder()
                            .bloodType(bloodType)
                            .totalEligibleForDonation(totalEligibleForDonation)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
