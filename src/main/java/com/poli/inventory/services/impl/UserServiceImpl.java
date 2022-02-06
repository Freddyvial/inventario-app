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
    public User consultUser(String userName, String password) {

        return userRepository.consultUser(userName, password);
    }

    @Override
    public User upDatePassword(User user) {
        return userRepository.upDatePassword(user);
    }

    @Override
    public List<User> consultUserByCampus(String idCampus) {
        return userRepository.consultUserByCampus(idCampus);
    }

    @Override
    public User setUser(User user, String idRole) throws Exception {
        User userExist = userRepository.consultUser(user.getUserName(), null);
        if (userExist == null) {
            if (user.getIdUser() == 0) {
                return userRepository.createUser(user);
            } else {
                if ((idRole.equals("2") || idRole.equals("3")) && user.getRole().getIdRole().equals("4")) {
                    int idMonitor = user.getIdUser();
                    user.setIdUser(0);
                    User newUser = userRepository.createUser(user);
                    User monitor = new User();
                    User model = new User();
                    monitor.setIdUser(idMonitor);
                    model.setIdUser(newUser.getIdUser());

                    return newUser;
                } else {
                    if (idRole.equals("2") && user.getRole().getIdRole().equals("3")) {
                        return userRepository.createUser(user);
                    }
                }
            }
        }
        return null;
    }
}
