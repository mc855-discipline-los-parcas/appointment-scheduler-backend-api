package br.unicamp.cecom.appointmentscheduler.core.features.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "admin")
public class AdminEntity {

    @Id
    private UUID adminId;

    @NotBlank(message = "Name must not be null and must contain at least one non-whitespace character")
    @Size(max = 255, message = "Name must have a maximum of 255 characters")
    private String fullname;

    @NotBlank(message = "Email must not be null and must contain at least one non-whitespace character")
    @Size(max = 50, message = "Email must have a maximum of 50 characters")
    private String email;

    @NotBlank(message = "Phone must not be null and must contain at least one non-whitespace character")
    @Size(max = 15, message = "Phone must have a maximum of 15 characters")
    private String phone;

    @PrePersist
    public void prePersist() {
        this.adminId = UUID.randomUUID();
    }
}
