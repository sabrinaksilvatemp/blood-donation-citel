package com.kappann.citel.usecases.impl;

import com.kappann.citel.application.dtos.PersonDTO;
import com.kappann.citel.application.dtos.response.OverWeightByGenderResponseDTO;
import com.kappann.citel.domain.enums.BMICategory;
import com.kappann.citel.domain.enums.Gender;
import com.kappann.citel.usecases.OverWeightByGenderUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OverWeightByGenderUseCaseImpl implements OverWeightByGenderUseCase {
    @Override
    public List<OverWeightByGenderResponseDTO> calculateObesityPercentageByGender(List<PersonDTO> candidates) {
        Map<Gender, List<PersonDTO>> groupedByGender = candidates.stream()
                .filter(candidate -> candidate.getGender() != null && candidate.getBmi() != null && candidate.getBmi().getCategory() != null)
                .collect(Collectors.groupingBy(PersonDTO::getGender));

        return groupedByGender.entrySet().stream()
                .map(entry -> {
                    Gender gender = entry.getKey();
                    List<PersonDTO> personsInGenderGroup = entry.getValue();

                    long totalInGenderGroup = personsInGenderGroup.size();

                    long obeseCount = personsInGenderGroup.stream()
                            .filter(person -> person.getBmi().getCategory() == BMICategory.OBESITY)
                            .count();

                    String obesityPercentage = totalInGenderGroup > 0
                            ? String.format("%.2f%%", (obeseCount * 100.0) / totalInGenderGroup)
                            : "0.00%";

                    return OverWeightByGenderResponseDTO.builder()
                            .gender(gender.getDescription())
                            .percentage(obesityPercentage)
                            .build();
                })
                .toList();    }
}
