package com.poli.inventory.repositories;


import com.poli.inventory.domain.User;

import java.util.List;


public interface UserRepository {
    User consultUser(String userName, String password);
    User createUser(User user);
    User upDatePassword(User user);
    List<User> consultUserByCampus(String idCampus);
}
