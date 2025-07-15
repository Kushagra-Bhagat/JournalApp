package com.kushagra.journalApp.controller;

import com.kushagra.journalApp.cache.AppCache;
import com.kushagra.journalApp.entity.User;
import com.kushagra.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private AppCache appCache;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAll();
        if (users != null && !users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(users, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public void createAdmin(@RequestBody User user) {
        userService.saveNewAdminUser(user);
    }

    @GetMapping("/reset-app-cache")
    public void resetAppCache() {
        appCache.init();
    }
}
