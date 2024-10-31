package com.kappann.citel.adapters.repositories;

import com.kappann.citel.domain.entities.BloodTypeCompatibility;
import com.kappann.citel.domain.enums.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BloodTypeCompatibilityRepository extends JpaRepository<BloodTypeCompatibility, Long> {

    @Query("SELECT b.donor.type FROM BloodTypeCompatibility b WHERE b.recipient.type = :bloodType")
    List<BloodType> findEligibleDonorsForRecipient(@Param("bloodType") BloodType bloodType);

    List<BloodTypeCompatibility> findByRecipient_Type(BloodType bloodType);

}
