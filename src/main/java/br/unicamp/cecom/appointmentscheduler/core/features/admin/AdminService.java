package br.unicamp.cecom.appointmentscheduler.core.features.admin;

import br.unicamp.cecom.appointmentscheduler.core.exception.NotFoundException;
import br.unicamp.cecom.appointmentscheduler.core.features.admin.to.request.CreateAdminRequest;
import br.unicamp.cecom.appointmentscheduler.core.features.admin.to.request.UpdateAdminRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminEntity create(final CreateAdminRequest request) {

        return adminRepository.save(AdminEntity.builder()
                .adminId(UUID.randomUUID())
                .email(request.getEmail())
                .fullname(request.getFullName())
                .phone(request.getPhone())
                .build());
    }

    public void update(final UUID adminId, final UpdateAdminRequest request) {

        try {
            AdminEntity admin = adminRepository.getOne(adminId);

            admin.setEmail(request.getEmail());
            admin.setFullname(request.getFullName());
            admin.setPhone(request.getPhone());

            adminRepository.save(admin);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("message.admin.notFound");
        }
    }

//    public void delete(final UUID adminId) {
//        if (this.adminRepository.delete(adminId) == 0) {
//            throw new NotFoundException("message.admin.notFound");
//        }
//    }
//
//    public Optional<AdminEntity> findById(final UUID adminId) {
//        return Optional.ofNullable(this.adminRepository.findById(adminId))
//            .orElseThrow(() -> new NotFoundException("message.admin.notFound"));
//
//    }
//    public List<AdminEntity> listAdmins() {
//        return this.adminRepository.listAdmins();
//    }
}
