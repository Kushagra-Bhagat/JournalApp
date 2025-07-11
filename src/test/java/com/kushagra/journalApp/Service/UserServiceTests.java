package com.kushagra.journalApp.Service;

import com.kushagra.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Disabled
@Tag("ignore-sonar")
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Disabled
    public void findByUserNameTest() {
        assertNotNull(userRepository.findByUsername("Ram"));
    }

    @ParameterizedTest
    @Disabled
    @ValueSource(strings = {
            "Ram",
            "Shyam",
            "Kushagra",
            "Amit"
    })
    public void findByUsernameTest(String username) {
        assertNotNull(userRepository.findByUsername(username));
    }
}
