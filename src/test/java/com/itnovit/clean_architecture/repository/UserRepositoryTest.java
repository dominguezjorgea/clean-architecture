package com.itnovit.clean_architecture.repository;

import com.itnovit.clean_architecture.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@ActiveProfiles("test")
@EntityScan(basePackages = "com.itnovit.clean_architecture.entity")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindAll() {
        // Save and test entities
        User user1 = new User("John Doe", "john.doe@example.com");
        User user2 = new User("Jane Smith", "jane.smith@example.com");

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> users = userRepository.findAll();

        assertThat(users).hasSize(2);
        assertThat(users).extracting(User::getName).contains("John Doe", "Jane Smith");
    }
}
