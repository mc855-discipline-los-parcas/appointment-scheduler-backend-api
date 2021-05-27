package br.unicamp.cecom.appointmentscheduler.core.features.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface AdminRepository extends JpaRepository<AdminEntity, UUID> {

}
