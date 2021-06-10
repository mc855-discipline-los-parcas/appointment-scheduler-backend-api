package br.unicamp.cecom.appointmentscheduler.core.features.patient.to.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAdminRequest {
    @NotBlank(message = "CPF must not be null and must contain at least one non-whitespace character")
    @Max(value = 11, message = "CPF must have a maximum of 11 characters")
    @Min(value = 11, message = "CPF must have a minimum of 11 characters")
    @Pattern(regexp="([0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}-[0-9]{2})|([0-9]{11})", message = "Invalid CPF number")
    private String cpf;

    @NotBlank(message = "Name must not be null and must contain at least one non-whitespace character")
    @Max(value = 255, message = "Name must have a maximum of 255 characters")
    private String fullName;

    @NotBlank(message = "Email must not be null and must contain at least one non-whitespace character")
    @Max(value = 50, message = "Email must have a maximum of 50 characters")
    private String email;

    @NotBlank(message = "Phone must not be null and must contain at least one non-whitespace character")
    @Pattern(regexp = "^(?:(?:\\+|00)?(55)\\s?)?(?:\\(?([1-9][0-9])\\)?\\s?)?(?:((?:9\\d|[2-9])\\d{3})\\-?(\\d{4}))$", message = "Invalid phone number")
    private String phone;
}
