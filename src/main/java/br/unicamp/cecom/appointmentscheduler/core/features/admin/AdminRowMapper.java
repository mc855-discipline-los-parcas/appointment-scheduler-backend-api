package br.unicamp.cecom.appointmentscheduler.core.features.admin;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

class AdminRowMapper implements RowMapper<AdminEntity> {
    @Override
    public AdminEntity mapRow(ResultSet rs, int i) throws SQLException{
        return AdminEntity.builder()
                .adminId(UUID.fromString(rs.getString("ADMIN_ID")))
                .fullname(rs.getString("FULLNAME"))
                .email(rs.getString("EMAIL"))
                .phone(rs.getString("PHONE"))
                .build();
    }
}
