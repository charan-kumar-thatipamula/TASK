package com.kastech.controller;

import com.kastech.model.User;
import com.kastech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/createUser")
    public User createUser() {
        User user = new User();
        user.setFirstName("fName1");
        user.setLastName("lName1");
        user.setEmail("test@gmail.com");
        user = userRepository.save(user);
        return user;
    }
}
