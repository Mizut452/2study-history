package Mizut452.studyhistory;

import Mizut452.studyhistory.HomeController.UserList;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LoginUserRepository {
    private static final String SQL_FIND_BY_USERNAME = """
            SELECT
            u.id,
            u.mailaddress,
            u.username,
            u.password
            FROM userlist u
            WHERE u.username = ?
            """;

    private static final ResultSetExtractor<UserList> LOGIN_USER_RESULT_SET_EXTRACTOR = (rs) -> {
        String id = null;
        String mailaddress = null;
        String username = null;
        String password = null;

        while(rs.next()) {
            if (username == null) {
                id = rs.getString("id");
                mailaddress = rs.getString("mailaddress");
                username = rs.getString("username");
                password = rs.getString("password");
            }
        }
        if (username == null) {
            return null;
        }
        return  new UserList(id, mailaddress, username, password);
    };

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public LoginUserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    public Optional<UserList> findByUsername(String username) {
        MapSqlParameterSource params = new MapSqlParameterSource("username", username);

        UserList userLists= namedParameterJdbcTemplate.query(SQL_FIND_BY_USERNAME, params, LOGIN_USER_RESULT_SET_EXTRACTOR);
        return Optional.ofNullable(userLists);
    }

}
