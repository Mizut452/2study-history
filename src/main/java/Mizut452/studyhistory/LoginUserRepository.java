package Mizut452.studyhistory;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LoginUserRepository {
    private static final String SQL_FIND_BY_USERNAME = """
            SELECT
            username,
            password
            FROM userlist
            WHERE username = ?
            """;

    private static final ResultSetExtractor<LoginUser> LOGIN_USER_RESULT_SET_EXTRACTOR = (rs) -> {
        String id = null;
        String mailaddress = null;
        String username = null;
        String password = null;

        while(rs.next()) {
            if (username == null) {
                username = rs.getString("username");
                password = rs.getString("password");
            }
        }
        if (username == null) {
            return null;
        }
        return  new LoginUser(id, mailaddress, username, password);
    };

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public LoginUserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    public Optional<LoginUser> findByUsername(String username) {
        MapSqlParameterSource params = new MapSqlParameterSource("username", username);

        LoginUser loginUser= namedParameterJdbcTemplate.query(SQL_FIND_BY_USERNAME, params, LOGIN_USER_RESULT_SET_EXTRACTOR);
        return Optional.ofNullable(loginUser);
    }

}
