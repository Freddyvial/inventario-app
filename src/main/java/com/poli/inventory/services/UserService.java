package com.poli.inventory.services;


import com.poli.inventory.domain.User;

import java.util.List;

public interface UserService {

    User consultUser(String userName, String password);
    public


    User setUser(User user,String idRole) throws Exception;
    public User upDatePassword(User user);
    List<User> consultUserByCampus(String idCampus);
}
