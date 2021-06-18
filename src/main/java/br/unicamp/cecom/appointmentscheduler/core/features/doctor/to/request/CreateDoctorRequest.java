package br.unicamp.cecom.appointmentscheduler.core.features.doctor.to.request;

import br.unicamp.cecom.appointmentscheduler.core.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDoctorRequest {

    @NotBlank(message = "Name must not be null and must contain at least one non-whitespace character")
    @Max(value = 255, message = "Name must have a maximum of 255 characters")
    private String fullName;

    @NotBlank(message = "Email must not be null and must contain at least one non-whitespace character")
    @Max(value = 50, message = "Email must have a maximum of 50 characters")
    private String email;

    @NotBlank(message = "Phone must not be null and must contain at least one non-whitespace character")
    @Pattern(regexp = "^(?:(?:\\+|00)?(55)\\s?)?(?:\\(?([1-9][0-9])\\)?\\s?)?(?:((?:9\\d|[2-9])\\d{3})\\-?(\\d{4}))$", message = "Invalid phone number")
    private String phone;

    @NotBlank(message = "CRM must not be null and must contain at least one non-whitespace character")
    @Max(value = 50, message = "CRM must have a maximum of 50 characters")
    private String crm;

    @NotBlank(message = "Specialty must be not null")
    @Max(value = 50, message = "Specialty must have a maximum of 50 characters")
    private String specialty;

    @NotNull(message = "Appointment Duration must be not null")
    private Integer appointmentDuration;
}
