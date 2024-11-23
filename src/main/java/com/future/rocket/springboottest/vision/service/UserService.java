package com.future.rocket.springboottest.vision.service;

import com.future.rocket.springboottest.vision.model.User;
import com.future.rocket.springboottest.vision.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User createUser(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        return userRepository.save(user);
    }

    public long countUsers() {
        return userRepository.count();
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
