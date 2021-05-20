package br.unicamp.cecom.appointmentscheduler.core.features.admin;

import br.unicamp.cecom.appointmentscheduler.core.features.admin.to.request.CreateAdminRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/admin", produces = APPLICATION_JSON_VALUE)
public class AdminRestController {

    private final AdminService adminService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity create(@Validated @RequestBody CreateAdminRequest request){

        final UUID adminId = adminService.create(request);

        return created(URI.create(format("/api/v1/admin/%s", adminId))).build();
    }

    @DeleteMapping(value = "/{adminId}")
    public ResponseEntity delete(@Validated @PathVariable UUID adminId){
        adminService.delete(adminId);

        return noContent().build();
    }

    @GetMapping(value = "/{adminId}" )
    public ResponseEntity findById(@Validated @PathVariable UUID adminId){
        return Optional.ofNullable(adminService.findById(adminId))
                .map(admin -> ResponseEntity.ok().body(admin))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
