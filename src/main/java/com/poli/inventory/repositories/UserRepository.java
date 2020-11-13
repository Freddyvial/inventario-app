package com.poli.inventory.repositories;


import com.poli.inventory.domain.User;


public interface UserRepository {
    User consultUser(String userName, String password);
    User createUser(User user);
    User upDatePassword(User user);
}
