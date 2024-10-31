package com.kappann.citel.application.services.impl;

import com.kappann.citel.application.dtos.BmiDTO;
import com.kappann.citel.application.dtos.PersonDTO;
import com.kappann.citel.application.dtos.request.PersonRequestDTO;
import com.kappann.citel.application.dtos.response.*;
import com.kappann.citel.application.services.BloodDonationDataProcessorService;
import com.kappann.citel.domain.enums.BMICategory;
import com.kappann.citel.usecases.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class BloodDonationDataProcessorServiceImpl implements BloodDonationDataProcessorService {

    private CandidatesByBloodTypeUseCase candidatesByBloodTypeUseCase;
    private AgeAverageByBloodTypeUseCase ageAverageByBloodTypeUseCase;
    private OverWeightByGenderUseCase overWeightByGenderUseCase;
    private AverageBmiByAgeGroupUseCase averageBmiByAgeGroupUseCase;
    private CandidatesByStateUseCase candidatesByStateUseCase;

    private static final double BMI_UNDERWEIGHT = 18.5;
    private static final double BMI_NORMAL_UPPER = 24.9;
    private static final double BMI_OVERWEIGHT_UPPER = 29.9;

    @Override
    public ResponseDTO processDonationCandidatesData(List<PersonRequestDTO> candidatesRequest) {

        List<PersonDTO> candidates  = candidatesRequest
                .stream()
                .map(candidate -> new PersonDTO(candidate.getBirthDate(), candidate.getGender(), candidate.getState(), candidate.getHeight(), candidate.getWeight(), candidate.getBloodType()))
                .peek(personDTO -> {
                    BmiDTO bmiDTO = calculateBmi(personDTO);
                    personDTO.setBmi(bmiDTO);
                })
                .toList();


        List<PersonDTO> eligibleCandidates = getEligibleDonorsCandidates(candidates);
        List<PersonDTO> notEligibleCandidates = getNonEligibleDonorsCandidates(candidates);


        List<TotalByStateResponseDTO> electiveDonorsByState = this.candidatesByStateUseCase.getTotalByState(eligibleCandidates);
        List<TotalByStateResponseDTO> nonElectiveDonorsByState = this.candidatesByStateUseCase.getTotalByState(notEligibleCandidates);
        List<TotalByStateResponseDTO> totalCandidatesByState = this.candidatesByStateUseCase.getTotalByState(candidates);

        List<AverageBMIResponseDTO> electiveDonorsAverageBMI = this.averageBmiByAgeGroupUseCase.calculateAverageBmiByAgeGroup(eligibleCandidates);
        List<AverageBMIResponseDTO> nonElectiveDonorsAverageBMI = this.averageBmiByAgeGroupUseCase.calculateAverageBmiByAgeGroup(notEligibleCandidates);
        List<AverageBMIResponseDTO> totalCandidatesAverageBMI = this.averageBmiByAgeGroupUseCase.calculateAverageBmiByAgeGroup(candidates);

        List<OverWeightByGenderResponseDTO> electiveDonorsOverWeightByGender = this.overWeightByGenderUseCase.calculateObesityPercentageByGender(eligibleCandidates);
        List<OverWeightByGenderResponseDTO> nonElectiveDonorsOverWeightByGender = this.overWeightByGenderUseCase.calculateObesityPercentageByGender(notEligibleCandidates);
        List<OverWeightByGenderResponseDTO> totalCandidatesOverWeightByGender = this.overWeightByGenderUseCase.calculateObesityPercentageByGender(candidates);

        List<AgeAverageByBloodTypeResponseDTO> electiveDonorsAgeAverageByBloodType = this.ageAverageByBloodTypeUseCase.calculateAverageAgeByBloodType(eligibleCandidates);
        List<AgeAverageByBloodTypeResponseDTO> nonElectiveDonorsAgeAverageByBloodType = this.ageAverageByBloodTypeUseCase.calculateAverageAgeByBloodType(notEligibleCandidates);
        List<AgeAverageByBloodTypeResponseDTO> totalCandidatesAgeAverageByBloodType = this.ageAverageByBloodTypeUseCase.calculateAverageAgeByBloodType(candidates);

        List<CandidatesByBloodTypeResponseDTO> donorsByBloodType = this.candidatesByBloodTypeUseCase.getTotalEligibleForDonationByBloodType(eligibleCandidates);

        return ResponseDTO.builder()
                .electiveDonorsByState(electiveDonorsByState)
                .nonElectiveDonorsByState(nonElectiveDonorsByState)
                .totalCandidatesByState(totalCandidatesByState)
                .electiveDonorsAverageBMI(electiveDonorsAverageBMI)
                .nonElectiveDonorsAverageBMI(nonElectiveDonorsAverageBMI)
                .totalCandidatesAverageBMI(totalCandidatesAverageBMI)
                .electiveDonorsOverWeightByGender(electiveDonorsOverWeightByGender)
                .nonElectiveDonorsOverWeightByGender(nonElectiveDonorsOverWeightByGender)
                .totalCandidatesOverWeightByGender(totalCandidatesOverWeightByGender)
                .electiveDonorsAgeAverageByBloodType(electiveDonorsAgeAverageByBloodType)
                .nonElectiveDonorsAgeAverageByBloodType(nonElectiveDonorsAgeAverageByBloodType)
                .totalCandidatesAgeAverageByBloodType(totalCandidatesAgeAverageByBloodType)
                .donorsByBloodType(donorsByBloodType)
                .build();


    }

    private List<PersonDTO> filterCandidates(List<PersonDTO> candidates, Predicate<PersonDTO> condition) {
        return candidates.stream()
                .filter(condition)
                .toList();
    }

    private List<PersonDTO> getEligibleDonorsCandidates(List<PersonDTO> candidates) {
        return filterCandidates(candidates, PersonDTO::canDonate);
    }

    private List<PersonDTO> getNonEligibleDonorsCandidates(List<PersonDTO> candidates) {
        return filterCandidates(candidates, candidate -> !candidate.canDonate());
    }
    private BmiDTO calculateBmi(PersonDTO person) {
        Double bmiValue = person.getWeight() / Math.pow(person.getHeight(), 2);
        bmiValue = roundToTwoDecimalPlaces(bmiValue);
        BMICategory category = determineBmiCategory(bmiValue);

        return BmiDTO.builder()
                .value(bmiValue)
                .category(category)
                .build();
    }

    private Double roundToTwoDecimalPlaces(Double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private BMICategory determineBmiCategory(Double bmiValue) {
        if (bmiValue < BMI_UNDERWEIGHT) {
            return BMICategory.UNDERWEIGHT;
        } else if (bmiValue <= BMI_NORMAL_UPPER) {
            return BMICategory.NORMAL;
        } else if (bmiValue <= BMI_OVERWEIGHT_UPPER) {
            return BMICategory.OVERWEIGHT;
        } else {
            return BMICategory.OBESITY;
        }
    }
}
