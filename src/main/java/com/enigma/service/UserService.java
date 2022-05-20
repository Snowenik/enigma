package com.enigma.service;

import com.enigma.dto.UserAddDTO;
import com.enigma.entity.User;
import com.enigma.exceptions.UserNotFoundException;

public interface UserService {

    Iterable<User> getUsers();
    void deleteUser(Long userId) throws UserNotFoundException;
    User addUser(UserAddDTO userAddDTO);
    User getUser(Long userId) throws UserNotFoundException;

}
