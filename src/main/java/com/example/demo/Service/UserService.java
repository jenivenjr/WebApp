package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUser() {
        return userRepository.findAll();
    }

    public boolean addNewUser(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        Optional<User> userByUserName = userRepository.findUserByUsername(user.getUsername());
        if (userByEmail.isPresent() || userByUserName.isPresent()) {
            throw new IllegalStateException("Email or Username Taken");
        } else {
            User savedUser = userRepository.save(user);
            if (savedUser != null) {
                return true;
            } else {
                return false;
            }
        }
    }

    public Optional<User> verifyCredentials(User user) {
        Optional<User> foundUser = null;
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            foundUser = userByEmail;
        }
        Optional<User> userByUserName = userRepository.findUserByUsername(user.getUsername());
        if (userByUserName.isPresent()) {
            foundUser = userByUserName;
        }
        if (userByEmail.isPresent() || userByUserName.isPresent()) {
            if (user.getPassword().equals(foundUser.get().getPassword())) {
                return foundUser;
            } else {
                throw new IllegalStateException("heyyy");
            }

        }else {
            throw new IllegalStateException("hey");
        }
    }
}

