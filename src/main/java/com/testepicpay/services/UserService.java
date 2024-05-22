package com.testepicpay.services;

import com.testepicpay.domain.user.User;
import com.testepicpay.domain.user.UserDTO;
import com.testepicpay.domain.user.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.testepicpay.repositories.UserRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(Long userId, BigDecimal amount) {
        User sender = findUserById(userId);
        if (sender.getType() == UserType.SELLER) {
            throw new RuntimeException("Seller user can't make transactions");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User createUser(UserDTO user) {
        User newUser = new User(user);;
        return userRepository.save(newUser);
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }
}
