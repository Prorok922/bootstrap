package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


public interface UserService extends UserDetailsService {
    List<User> getAllUsers ();
    User getUserById(long id);
    boolean saveUser(User user, String role);
    void removeUser(long id);
    void updateUser(User user, String role);
    User loadUserByUserEmail(String email);
}
