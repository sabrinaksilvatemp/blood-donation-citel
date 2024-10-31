package com.kappann.citel.usecases.impl;

import com.kappann.citel.application.dtos.PersonDTO;
import com.kappann.citel.application.dtos.response.TotalByStateResponseDTO;
import com.kappann.citel.usecases.CandidatesByStateUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CandidatesByStateUseCaseImpl implements CandidatesByStateUseCase {
    @Override
    public List<TotalByStateResponseDTO> getTotalByState(List<PersonDTO> candidates) {
        Map<String, Long> totalByStateMap = candidates
                .stream()
                .collect(Collectors.groupingBy(candidate ->
                        candidate.getState().name(), Collectors.counting()));

        return totalByStateMap.entrySet()
                .stream()
                .map(entry -> new TotalByStateResponseDTO(entry.getKey(), entry.getValue().intValue()))
                .toList();
    }
}
