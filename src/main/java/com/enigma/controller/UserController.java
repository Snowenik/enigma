package com.enigma.controller;

import com.enigma.dto.UserAddDTO;
import com.enigma.entity.User;
import com.enigma.exceptions.UserNotFoundException;
import com.enigma.repository.UserRepository;
import com.enigma.service.UserService;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @PostMapping("/users")
    public User addUser(@RequestBody UserAddDTO userAddDTO) {
        return userService.addUser(userAddDTO);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) throws UserNotFoundException {
        userService.deleteUser(userId);
    }

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable("userId") Long userId) throws UserNotFoundException {
        return userService.getUser(userId);
    }

    @GetMapping("/users")
    public Iterable<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(value = "/users", params = "firstName")
    public Iterable<User> findByFirstName(
            @Spec(path = "firstName", params = "firstName", spec = Like.class) Specification<User> spec,
            Pageable pageable) {

            return userRepository.findAll(spec, pageable);
    }

    @GetMapping(value = "/users", params = "lastName")
    public Iterable<User> findByLastName(
            @Spec(path = "lastName", params = "lastName", spec = Like.class) Specification<User> spec,
            Pageable pageable) {

            return userRepository.findAll(spec, pageable);
    }

    @GetMapping(value = "/users", params = {"firstName", "lastName"})
    public Iterable<User> findByFirstNameAndLastName(@And({
            @Spec(path = "firstName", params = "firstName", spec = Like.class),
            @Spec(path = "lastName", params = "lastName", spec = Like.class)}) Specification<User> spec, Pageable pageable) {

            return userRepository.findAll(spec, pageable);
    }

}














