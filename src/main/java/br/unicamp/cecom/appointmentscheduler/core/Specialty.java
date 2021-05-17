package br.unicamp.cecom.appointmentscheduler.core;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer specialtyId;

    @NotBlank(message = "Specialty name must not be null and must contain at least one non-whitespace character")
    @Max(value = 255, message = "Specialty name must have a maximum of 255 characters")
    private String specialtyName;
}
