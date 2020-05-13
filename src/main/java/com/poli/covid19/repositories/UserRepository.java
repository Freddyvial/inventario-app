package com.poli.covid19.repositories;


import com.poli.covid19.domain.User;

import java.util.List;

public interface UserRepository {

    List <User> getUser(String userName);
    User createUser(User user);

}
