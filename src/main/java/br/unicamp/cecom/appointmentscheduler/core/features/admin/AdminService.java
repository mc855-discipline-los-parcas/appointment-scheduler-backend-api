package br.unicamp.cecom.appointmentscheduler.core.features.admin;

import br.unicamp.cecom.appointmentscheduler.core.exception.NotFoundException;
import br.unicamp.cecom.appointmentscheduler.core.features.admin.to.request.CreateAdminRequest;
import br.unicamp.cecom.appointmentscheduler.core.features.admin.to.request.UpdateAdminRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public UUID create(final CreateAdminRequest request){
        return adminRepository.create(request);
    }

    public void update(final UUID adminId, final UpdateAdminRequest request) {
        if (this.adminRepository.update(adminId, request) == 0) {
            throw new NotFoundException("message.admin.notfound");
        }
    }

    public void delete(final UUID adminId) {
        if (this.adminRepository.delete(adminId) == 0) {
            throw new NotFoundException("message.admin.notfound");
        }
    }

    public Optional<AdminEntity> findById(final UUID adminId) {
        return this.adminRepository.findById(adminId);
    }
    public List<AdminEntity> listAdmins() {
        return this.adminRepository.listAdmins();
    }

}
