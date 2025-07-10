package com.kushagra.journalapp.Service;

import com.kushagra.journalapp.entity.User;
import com.kushagra.journalapp.repository.UserRepository;
import com.kushagra.journalapp.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @Test
    void loadByUsername() {
        when(userRepository.findByUsername(ArgumentMatchers.anyString()))
                .thenReturn(User.builder().username("Ram").password("Ram").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("Ram");
        Assertions.assertNotNull(user);
    }

}
