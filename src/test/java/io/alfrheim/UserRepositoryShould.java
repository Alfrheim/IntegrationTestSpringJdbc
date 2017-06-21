package io.alfrheim;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryShould {


    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .generateUniqueName(true)
                .addScript("db/sql/create-db.sql")
                .build();

        this.userRepository = new UserRepository(new JdbcTemplate(db));
    }

    @Test
    public void increase_the_number_of_users_when_user_is_added() throws Exception {
        userRepository.createUser("Largo Winch");
        userRepository.createUser("Jhon Wick");

        List<String> users = userRepository.getAllUsers();

        assertThat(users.size())
                .isEqualTo(2);
    }
}
