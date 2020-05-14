package com.poli.covid19.repositories;


import com.poli.covid19.domain.User;

import java.util.List;

public interface UserRepository {
    User getUser(String userName,String password);
    User createUser(User user);

}
