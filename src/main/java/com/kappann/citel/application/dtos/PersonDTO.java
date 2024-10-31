package com.kappann.citel.application.dtos;

import com.kappann.citel.domain.enums.BloodType;
import com.kappann.citel.domain.enums.Gender;
import com.kappann.citel.domain.enums.State;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
public class PersonDTO {
    private Integer age;
    private Gender gender;
    private State state;
    private Double height;
    private Double weight;
    private BloodType bloodType;
    private BmiDTO bmi;
    private String ageGroup;

    public PersonDTO(LocalDate birthDate, Gender gender, State state, Double height, Double weight, BloodType bloodType) {
        this.age = calculateAge(birthDate);
        this.gender = gender;
        this.state = state;
        this.height = height;
        this.weight = weight;
        this.bloodType = bloodType;
        this.ageGroup = calculateAgeGroup(age);
    }

    private int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public boolean canDonate() {
        return age >= 16 && age <= 69 && weight > 50;
    }

    public String calculateAgeGroup(int age) {
        int lowerBound = (age / 10) * 10 + (age % 10 == 0 && age != 0 ? -9 : 1);
        int upperBound = lowerBound + 9;

        return lowerBound + " - " + upperBound;
    }

}