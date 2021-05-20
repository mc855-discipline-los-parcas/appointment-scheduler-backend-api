package br.unicamp.cecom.appointmentscheduler.core.features.admin;

import br.unicamp.cecom.appointmentscheduler.core.features.admin.to.request.CreateAdminRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Repository
@Transactional
public class AdminRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;
    private static final AdminRowMapper ROW_MAPPER = new AdminRowMapper();

    private static final String CREATE = "INSERT INTO admin" +
            "(fullname, email, phone) VALUES (:fullname,:email,:phone) RETURNING admin_id";
    private static final String DELETE = "DELETE FROM admin WHERE admin_id = :adminId";
    private static final String FIND_BY_ID = "SELECT * FROM admin WHERE admin_id = ?";

    UUID create(final CreateAdminRequest request){
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("fullname", request.getFullName())
                .addValue( "email", request.getEmail())
                .addValue("phone", request.getPhone());

        return namedParameterJdbcTemplate.queryForObject(CREATE, namedParameters, UUID.class);
    }

    Integer delete(final UUID adminId){
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("adminId", adminId);

        return namedParameterJdbcTemplate.update(DELETE, namedParameters);
    }

    Optional<AdminEntity> findById(final UUID adminId){
        return jdbcTemplate.query(FIND_BY_ID, ROW_MAPPER, adminId).stream().findFirst();
    }
}
