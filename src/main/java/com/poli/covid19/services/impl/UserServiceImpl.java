package com.poli.covid19.services.impl;

import com.poli.covid19.domain.User;
import com.poli.covid19.repositories.UserRepository;
import com.poli.covid19.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUser(String userName) {

        return userRepository.getUser(userName);
    }

    @Override
    public User setUser(User user) {
        return userRepository.createUser(user);
    }


}
