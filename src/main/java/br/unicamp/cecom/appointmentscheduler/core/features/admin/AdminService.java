package br.unicamp.cecom.appointmentscheduler.core.features.admin;

import br.unicamp.cecom.appointmentscheduler.core.features.admin.to.request.CreateAdminRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public UUID create(CreateAdminRequest request){
        return adminRepository.create(request);
    }

    public void delete(UUID adminId) {
        this.adminRepository.delete(adminId);
    }

    public Optional<AdminEntity> findById(UUID adminId) {
        return this.adminRepository.findById(adminId);
    }

}
