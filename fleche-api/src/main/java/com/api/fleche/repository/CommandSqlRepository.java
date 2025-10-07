package com.api.fleche.repository;

import com.api.fleche.model.CommandSql;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommandSqlRepository extends JpaRepository<CommandSql, Long> {

    @Query(value = "SELECT CMD_SQL, CMD_ID, CMD_DESCRICAO, CMD_STATUS FROM TB_CMD WHERE CMD_ID = 1 AND CMD_STATUS = 'A'", nativeQuery = true)
    CommandSql allUsers();

    @Query(value = "SELECT CMD_SQL, CMD_ID, CMD_DESCRICAO, CMD_STATUS FROM TB_CMD WHERE CMD_ID = 2 AND CMD_STATUS = 'A'", nativeQuery = true)
    CommandSql findDataUser();

    @Query(value = "SELECT CMD_SQL, CMD_ID, CMD_DESCRICAO, CMD_STATUS FROM TB_CMD WHERE CMD_ID = 3 AND CMD_STATUS = 'A'", nativeQuery = true)
    CommandSql login();

    @Query(value = "SELECT CMD_SQL, CMD_ID, CMD_DESCRICAO, CMD_STATUS FROM TB_CMD WHERE CMD_ID = 4 AND CMD_STATUS = 'A'", nativeQuery = true)
    CommandSql usersOnline();

    @Query(value = "SELECT CMD_SQL, CMD_ID, CMD_DESCRICAO, CMD_STATUS FROM TB_CMD WHERE CMD_ID = 5 AND CMD_STATUS = 'A'", nativeQuery = true)
    CommandSql profileUser();
}
