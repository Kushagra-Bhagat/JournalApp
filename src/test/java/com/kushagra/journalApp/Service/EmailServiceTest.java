package com.kushagra.journalApp.Service;

import com.kushagra.journalApp.service.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void testEmail() {
        emailService.sendMail("bhagatkushagra@gmail.com",
                "Subject",
                "Body");
    }
}
