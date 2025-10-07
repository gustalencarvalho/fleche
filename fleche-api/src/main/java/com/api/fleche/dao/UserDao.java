package com.api.fleche.dao;

import com.api.fleche.model.dtos.LoginDto;
import com.api.fleche.model.dtos.ProfileUserDto;
import com.api.fleche.model.dtos.UserDataDto;
import com.api.fleche.enums.Gender;
import com.api.fleche.enums.Preferences;
import com.api.fleche.repository.CommandSqlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final CommandSqlRepository commandSqlRepository;

    public ProfileUserDto profileUser(Long userId) {
        String sql = commandSqlRepository.profileUser().getCmdSql();
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{userId}, (rs, rowNum) ->
                    new ProfileUserDto(
                            rs.getLong("USER_ID"),
                            Gender.valueOf(rs.getString("GENDER")),
                            rs.getString("BIO"),
                            rs.getBytes("PICTURE"),
                            rs.getString("FILME"),
                            rs.getString("LAZER"),
                            Preferences.valueOf(rs.getString("PREFERENCES"))
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public UserDataDto findDataUser(Long id) {
        String sql = commandSqlRepository.findDataUser().getCmdSql();
        List<UserDataDto> results = jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) ->
                new UserDataDto(
                        rs.getLong("ID"),
                        rs.getString("NAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PHONE"),
                        rs.getString("STATUS"),
                        rs.getString("STATUS_USER_BAR"),
                        rs.getString("NOME_BAR")
                )
        );

        return results.isEmpty() ? null : results.get(0);
    }

    public LoginDto findDataLogin(String phone) {
        String sql = commandSqlRepository.login().getCmdSql();
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{phone}, (rs, rowNum) ->
                    new LoginDto(
                            rs.getLong("ID"),
                            rs.getString("NOME"),
                            rs.getString("EMAIL"),
                            rs.getString("NUMERO"),
                            rs.getString("SENHA")
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
