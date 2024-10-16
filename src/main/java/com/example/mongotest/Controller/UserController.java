package com.example.mongotest.Controller;

import com.example.mongotest.model.User;
import com.example.mongotest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addUser")
    public void addUser(@RequestBody User user) {
        userRepository.save(user);
    }
    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    @GetMapping("/getUsers/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        return ResponseEntity.ok(user.orElse(null));
    }
    @PutMapping("/updateUsers/{id}")
    public Optional<User> updateUser(@PathVariable int id, @RequestBody User user) {
        return userRepository.findById(id).map( existingUser ->{
            existingUser.setName(user.getName());
            existingUser.setAddress(user.getAddress());
            return userRepository.save(existingUser);
        });
    }
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUsers(@PathVariable int id) {
        userRepository.deleteById(id);
    }
}
