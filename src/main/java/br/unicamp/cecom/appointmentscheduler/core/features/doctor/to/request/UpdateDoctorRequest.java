package br.unicamp.cecom.appointmentscheduler.core.features.doctor.to.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateDoctorRequest {

    @NotBlank(message = "Name must not be null and must contain at least one non-whitespace character")
    @Size(max = 255, message = "Name must have a maximum of 255 characters")
    private String fullName;

    @NotBlank(message = "Email must not be null and must contain at least one non-whitespace character")
    @Size(max = 50, message = "Email must have a maximum of 50 characters")
    private String email;

    @NotBlank(message = "Phone must not be null and must contain at least one non-whitespace character")
    @Pattern(regexp = "^(?:(?:\\+|00)?(55)\\s?)?(?:\\(?([1-9][0-9])\\)?\\s?)?(?:((?:9\\d|[2-9])\\d{3})\\-?(\\d{4}))$", message = "Invalid phone number")
    private String phone;

    @NotBlank(message = "CRM must not be null and must contain at least one non-whitespace character")
    @Size(max = 50, message = "CRM must have a maximum of 50 characters")
    private String crm;

    @NotBlank(message = "Specialty must be not null")
    @Size(max = 50, message = "Specialty must have a maximum of 50 characters")
    private String specialty;
}
