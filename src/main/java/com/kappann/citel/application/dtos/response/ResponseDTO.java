package com.kappann.citel.application.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseDTO {
    private List<TotalByStateResponseDTO> electiveDonorsByState;
    private List<TotalByStateResponseDTO> nonElectiveDonorsByState;
    private List<TotalByStateResponseDTO> totalCandidatesByState;


    private List<AverageBMIResponseDTO> electiveDonorsAverageBMI;
    private List<AverageBMIResponseDTO> nonElectiveDonorsAverageBMI;
    private List<AverageBMIResponseDTO> totalCandidatesAverageBMI;

    private List<OverWeightByGenderResponseDTO> electiveDonorsOverWeightByGender;
    private List<OverWeightByGenderResponseDTO> nonElectiveDonorsOverWeightByGender;
    private List<OverWeightByGenderResponseDTO> totalCandidatesOverWeightByGender;

    private List<AgeAverageByBloodTypeResponseDTO> electiveDonorsAgeAverageByBloodType;
    private List<AgeAverageByBloodTypeResponseDTO> nonElectiveDonorsAgeAverageByBloodType;
    private List<AgeAverageByBloodTypeResponseDTO> totalCandidatesAgeAverageByBloodType;

    private List<CandidatesByBloodTypeResponseDTO> donorsByBloodType;

}
