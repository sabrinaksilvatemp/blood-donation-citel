package com.kappann.citel.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "blood_types_compatibility")
public class BloodTypeCompatibility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "donor_blood_type_id", nullable = false)
    private BloodTypeDonation donor;

    @ManyToOne
    @JoinColumn(name = "recipient_blood_type_id", nullable = false)
    private BloodTypeDonation recipient;
}
