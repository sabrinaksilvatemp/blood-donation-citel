package com.kappann.citel.usecases.impl;

import com.kappann.citel.application.dtos.PersonDTO;
import com.kappann.citel.application.dtos.response.AgeAverageByBloodTypeResponseDTO;
import com.kappann.citel.domain.enums.BloodType;
import com.kappann.citel.usecases.AgeAverageByBloodTypeUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AgeAverageByBloodTypeUseCaseImpl implements AgeAverageByBloodTypeUseCase {
    @Override
    public List<AgeAverageByBloodTypeResponseDTO> calculateAverageAgeByBloodType(List<PersonDTO> candidates) {
        Map<BloodType, List<PersonDTO>> groupedByBloodType = candidates.stream()
                .filter(candidate -> candidate.getBloodType() != null && candidate.getAge() != null)
                .collect(Collectors.groupingBy(PersonDTO::getBloodType));

        return groupedByBloodType.entrySet().stream()
                .map(entry -> {
                    BloodType bloodType = entry.getKey();
                    List<PersonDTO> personsWithBloodType = entry.getValue();

                    Double averageAge = personsWithBloodType.stream()
                            .mapToInt(PersonDTO::getAge)
                            .average()
                            .orElse(0.0);

                    return AgeAverageByBloodTypeResponseDTO.builder()
                            .bloodType(bloodType.getDescription())
                            .averageAge(averageAge)
                            .build();
                })
                .toList();    }
}
