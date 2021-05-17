package br.unicamp.cecom.appointmentscheduler.core;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Doctor {

    @Id
    private UUID doctorId;

    @NotBlank(message = "Name must not be null and must contain at least one non-whitespace character")
    @Max(value = 255, message = "Name must have a maximum of 255 characters")
    private String fullname;

    @NotBlank(message = "Email must not be null and must contain at least one non-whitespace character")
    @Max(value = 50, message = "Email must have a maximum of 50 characters")
    private String email;

    @NotBlank(message = "Phone must not be null and must contain at least one non-whitespace character")
    @Max(value = 15, message = "Phone must have a maximum of 15 characters")
    private String phone;

    @PrePersist
    public void prePersist() {
        this.doctorId = UUID.randomUUID();
    }
}
