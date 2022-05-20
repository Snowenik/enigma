package com.enigma.service;

import com.enigma.dto.UserAddDTO;
import com.enigma.entity.Task;
import com.enigma.entity.User;
import com.enigma.exceptions.UserNotFoundException;
import com.enigma.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void deleteUser(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Requested user does not exist"));
        Task task = user.getTask();
        if (task != null) {
            task.removeUser(user);
        }
        userRepository.delete(user);
    }


    @Override
    public User addUser(UserAddDTO userAddDTO) {
        User user = new User();
        user.setFirstName(userAddDTO.getFirstName());
        user.setLastName(userAddDTO.getLastName());
        user.setEmail(userAddDTO.getEmail());
        user.setTask(null);
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUser(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Requested user does not exist"));
    }

    @Override
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

}














