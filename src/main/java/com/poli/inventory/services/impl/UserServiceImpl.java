package com.poli.inventory.services.impl;

import com.poli.inventory.domain.MonitorModel;
import com.poli.inventory.domain.User;
import com.poli.inventory.repositories.MonitorModelRepository;
import com.poli.inventory.repositories.UserRepository;
import com.poli.inventory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MonitorModelRepository monitorModelRepository;

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
                if ((idRole.equals("2") || idRole.equals("5")) && user.getRole().getIdRole().equals("3")) {
                    int idMonitor = user.getIdUser();
                    user.setIdUser(0);
                    User newUser = userRepository.createUser(user);
                    MonitorModel monitorModel = new MonitorModel();
                    User monitor = new User();
                    User model = new User();
                    monitor.setIdUser(idMonitor);
                    model.setIdUser(newUser.getIdUser());
                    monitorModel.setUserMonitor(monitor);
                    monitorModel.setUserModel(model);
                    monitorModelRepository.create(monitorModel);
                    return newUser;
                } else {
                    if (idRole.equals("2") && user.getRole().getIdRole().equals("5")) {
                        return userRepository.createUser(user);
                    }
                }
            }
        }
        return null;
    }
}
