package com.kappann.citel.usecases.impl;

import com.kappann.citel.application.dtos.PersonDTO;
import com.kappann.citel.application.dtos.response.AverageBMIResponseDTO;
import com.kappann.citel.usecases.AverageBmiByAgeGroupUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AverageBmiByAgeGroupUseCaseImpl implements AverageBmiByAgeGroupUseCase {
    @Override
    public List<AverageBMIResponseDTO> calculateAverageBmiByAgeGroup(List<PersonDTO> candidates) {
        Map<String, List<PersonDTO>> groupedByAgeGroup = candidates.stream()
                .filter(candidate -> candidate.getBmi() != null && candidate.getBmi().getValue() != null)
                .collect(Collectors.groupingBy(PersonDTO::getAgeGroup));

        return groupedByAgeGroup.entrySet().stream()
                .map(entry -> {
                    String ageGroup = entry.getKey();
                    List<PersonDTO> personsInGroup = entry.getValue();

                    Double averageBmi = personsInGroup.stream()
                            .mapToDouble(person -> person.getBmi().getValue())
                            .average()
                            .orElse(0.0);

                    return AverageBMIResponseDTO.builder()
                            .ageGroup(ageGroup)
                            .averageBmi(averageBmi)
                            .build();
                })
                .collect(Collectors.toList());    }
}
