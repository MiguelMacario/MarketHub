package com.macariomiguel.ecommerceproject.service;

import com.macariomiguel.ecommerceproject.dto.RegisterDTO;
import com.macariomiguel.ecommerceproject.entity.User;
import com.macariomiguel.ecommerceproject.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByLogin(String login){
        return userRepository.findByLogin(login);
    }

    public void register(RegisterDTO data){
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());
        userRepository.save(newUser);
    }

}
