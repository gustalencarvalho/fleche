package com.api.fleche.dao;

import com.api.fleche.model.dtos.LocationDto;
import com.api.fleche.model.dtos.UserLocationDto;
import com.api.fleche.repository.CommandSqlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
            LocationDto dto = new LocationDto();
            dto.setId(rs.getLong("LOCATION_ID"));
            dto.setName(rs.getString("NAME"));
            dto.setAddress(rs.getString("ADDRESS"));
            dto.setDistrict(rs.getString("DISTRICT"));
            dto.setCity(rs.getString("CITY"));
            dto.setQrCode(rs.getString("QR_CODE"));
            dto.setUsersOnline(rs.getLong("TOTAL_USUARIOS"));
            return dto;
        }, usuarioId);
    }

}
