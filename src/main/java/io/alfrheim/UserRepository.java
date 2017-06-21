package io.alfrheim;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserRepository {
    
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getAllUsers() {
        return jdbcTemplate.queryForList("SELECT NAME FROM USERS", String.class);
    }

    public void createUser(String userName) {
        jdbcTemplate.update("INSERT INTO USERS(NAME) VALUES(?)", userName);
    }
}
