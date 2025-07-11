package com.kushagra.journalapp.controller;

import com.kushagra.journalapp.entity.User;
import com.kushagra.journalapp.response.api.QuoteResponse;
import com.kushagra.journalapp.service.QuoteService;
import com.kushagra.journalapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuoteService quoteService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findByUserName(username);
        userInDb.setUsername(user.getUsername());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody User user) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userService.deleteByUsername(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greetings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ResponseEntity<List<QuoteResponse>> response = quoteService.getQuote();
        List<QuoteResponse> quotes = response.getBody();
        String quote = "";
        if (quotes != null && !quotes.isEmpty()) {
            quote = quotes.get(0).getQuote();
        }
        return new ResponseEntity<>("Hi " + username + ".\nQuote of the day is: " + quote, HttpStatus.OK);
    }
}
