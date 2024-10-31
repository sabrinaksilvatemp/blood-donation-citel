package com.kappann.citel.domain.entities;

import com.kappann.citel.domain.enums.BloodType;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Entity
@Getter
@Table(name = "blood_types_donation")
public class BloodTypeDonation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false, unique = true)
    @Enumerated(EnumType.ORDINAL)
    private BloodType type;

    @OneToMany(mappedBy = "donor")
    private Set<BloodTypeCompatibility> eligibleDonors;

    @OneToMany(mappedBy = "recipient")
    private Set<BloodTypeCompatibility> eligibleRecipients;

}
