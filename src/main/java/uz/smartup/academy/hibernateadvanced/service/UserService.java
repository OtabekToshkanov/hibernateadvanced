package uz.smartup.academy.hibernateadvanced.service;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.smartup.academy.hibernateadvanced.entity.Role;
import uz.smartup.academy.hibernateadvanced.entity.User;
import uz.smartup.academy.hibernateadvanced.repository.UserRepository;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerUser(User user, Set<Role> roles) {
        for (Role role : roles) {
            role.setUsername(user.getUsername());
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        user.setRoles(roles);

        userRepository.save(user);
    }

}
