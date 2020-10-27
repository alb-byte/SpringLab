package by.shymanel.springlab.service.impl;

import by.shymanel.springlab.model.Role;
import by.shymanel.springlab.model.User;
import by.shymanel.springlab.repository.RoleRepository;
import by.shymanel.springlab.repository.UserRepository;
import by.shymanel.springlab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        User registeredUser = userRepository.save(user);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User findByUsername(String name) {
        User user = userRepository.findByUsername(name);
        return user;
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}

