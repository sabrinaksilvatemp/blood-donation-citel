package com.kappann.citel.application.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kappann.citel.domain.enums.BloodType;
import com.kappann.citel.domain.enums.Gender;
import com.kappann.citel.domain.enums.State;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PersonRequestDTO {

    @JsonProperty("nome")
    private String name;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("rg")
    private String rg;

    @JsonProperty("data_nasc")
    @NotNull(message = "<data_nasc> must be provided in pattern <dd/MM/yyyy>")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @JsonProperty("sexo")
    @NotNull(message = "<sexo> must be provided")
    private Gender gender;

    @JsonProperty("mae")
    private String motherName;

    @JsonProperty("pai")
    private String fatherName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("cep")
    private String postalCode;

    @JsonProperty("endereco")
    private String address;

    @JsonProperty("numero")
    private Integer addressNumber;

    @JsonProperty("bairro")
    private String neighborhood;

    @JsonProperty("cidade")
    private String city;

    @JsonProperty("estado")
    @NotNull(message = "<estado> must be provided")
    private State state;

    @JsonProperty("telefone_fixo")
    private String landlinePhone;

    @JsonProperty("celular")
    private String mobilePhone;

    @JsonProperty("altura")
    @NotNull(message = "<altura> must be provided")
    @Positive(message = "<altura> must be positive")
    private Double height;

    @JsonProperty("peso")
    @NotNull(message = "<peso> must be provided")
    @Positive(message = "<peso> must be positive")
    private Double weight;

    @JsonProperty("tipo_sanguineo")
    @NotNull(message = "<tipo_sanguineo> must be provided")
    private BloodType bloodType;
}
