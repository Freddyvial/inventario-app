package com.poli.inventory.services;


import com.poli.inventory.domain.User;

import java.util.List;

public interface UserService {

    User consultUser(String userName, String password);
    User setUser(User user);
    public User upDatePassword(User user);
    List<User> consultUserByCampus(String idCampus);
}
