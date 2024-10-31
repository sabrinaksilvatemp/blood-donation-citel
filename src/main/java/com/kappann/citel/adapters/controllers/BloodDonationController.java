package com.kappann.citel.adapters.controllers;

import com.kappann.citel.application.dtos.request.PersonRequestDTO;
import com.kappann.citel.application.dtos.response.ResponseDTO;
import com.kappann.citel.application.services.BloodDonationDataProcessorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/blood-donation")
@Validated
public class BloodDonationController {

    private BloodDonationDataProcessorService service;

    @GetMapping("/candidates/process")
    public ResponseEntity<ResponseDTO> processCandidates(
            @Valid @RequestBody List<PersonRequestDTO> candidatesRequest) {
        return ResponseEntity.ok(service.processDonationCandidatesData(candidatesRequest));
    }

}