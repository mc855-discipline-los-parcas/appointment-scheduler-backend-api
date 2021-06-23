package br.unicamp.cecom.appointmentscheduler.core.features.admin;

import br.unicamp.cecom.appointmentscheduler.core.exceptions.NotFoundException;
import br.unicamp.cecom.appointmentscheduler.core.features.admin.to.request.CreateAdminRequest;
import br.unicamp.cecom.appointmentscheduler.core.features.admin.to.request.UpdateAdminRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
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

    public void delete(final UUID adminId) {
        try {
            adminRepository.deleteById(adminId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("message.admin.notFound");
        }
    }

    public Optional<AdminEntity> findById(final UUID adminId) {
        Optional<AdminEntity> adminEntity = adminRepository.findById(adminId);
        if(adminEntity.isPresent()) {
            return adminEntity;
        } else{
            throw new NotFoundException("message.admin.notFound");
        }
    }

    public List<AdminEntity> listAdmins() {
        return this.adminRepository.findAll();
    }
}
