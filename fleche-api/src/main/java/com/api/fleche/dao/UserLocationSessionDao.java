package com.api.fleche.dao;

import com.api.fleche.model.dtos.LocationDto;
import com.api.fleche.model.dtos.UserLocationDto;
import com.api.fleche.repository.CommandSqlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserLocationSessionDao {

    private final JdbcTemplate jdbcTemplate;
    private final CommandSqlRepository commandSqlRepository;

    public List<UserLocationDto> usuariosParaListar(String qrCode, Long usuarioId) {
        String sql = commandSqlRepository.allUsers().getCmdSql();
        List<UserLocationDto> resultados = jdbcTemplate.query(sql, new Object[]{qrCode, usuarioId, usuarioId},
                (rs, rowNum) -> new UserLocationDto(
                        rs.getLong("ID"),
                        rs.getString("NAME"),
                        rs.getString("GENDER"),
                        rs.getInt("AGE")
                )
        );
        return resultados;
    }

    public List<LocationDto> listarTotalUsuariosPorBar(Long usuarioId) {
        String sql = commandSqlRepository.usersOnline().getCmdSql();
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            LocationDto dto = LocationDto.builder()
                    .id(rs.getLong("LOCATION_ID"))
                    .name(rs.getString("NAME"))
                    .address(rs.getString("ADDRESS"))
                    .district(rs.getString("DISTRICT"))
                    .city(rs.getString("CITY"))
                    .qrCode(rs.getString("QR_CODE"))
                    .usersOnline(rs.getLong("TOTAL_USUARIOS"))
                    .build();
            return dto;
        }, usuarioId);
    }

}
