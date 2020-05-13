package com.poli.covid19.services;


import com.poli.covid19.domain.User;

import java.util.List;

public interface UserService {

    List<User> getUser(String userName);
    User setUser(User user);

}
