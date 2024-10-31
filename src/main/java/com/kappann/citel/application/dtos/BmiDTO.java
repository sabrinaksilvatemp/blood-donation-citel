package com.kappann.citel.application.dtos;

import com.kappann.citel.domain.enums.BMICategory;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BmiDTO {
    private BMICategory category;
    private Double value;
}
