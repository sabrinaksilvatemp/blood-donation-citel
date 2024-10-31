package com.kappann.citel.application.dtos.response;

import com.kappann.citel.domain.enums.BloodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CandidatesByBloodTypeResponseDTO {
    private Double totalEligibleForDonation;
    private BloodType bloodType;
}
