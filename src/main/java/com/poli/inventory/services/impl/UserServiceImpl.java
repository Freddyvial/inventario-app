package com.poli.inventory.services.impl;

import com.poli.inventory.domain.User;
import com.poli.inventory.repositories.UserRepository;
import com.poli.inventory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User consultUser(String userName,String password) {

        return userRepository.consultUser(userName,password);
    }

    @Override
    public User setUser(User user) {
        return userRepository.createUser(user);
    }
    @Override
    public User upDatePassword(User user) {
        return userRepository.upDatePassword(user);
    }

    @Override
    public List<User> consultUserByCampus(String idCampus) {
        return userRepository.consultUserByCampus(idCampus);
    }

}
