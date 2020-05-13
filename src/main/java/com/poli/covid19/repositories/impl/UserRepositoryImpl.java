package com.poli.covid19.repositories.impl;


import com.poli.covid19.domain.User;
import com.poli.covid19.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getUser(String userName) {
        String sql = "";

            sql = "select userName from covid19.town where userName= "+userName;


        List<User> user = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
        if (user.get(0)==null){
            return null;
        }else{
            return user ;
        }


    }

    public User createUser(User user) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql= "INSERT INTO covid19.users (userName,passWord,idRole) values(?,?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getUserName());
                ps.setString(2, user.getPassWord());
                ps.setInt(3, Integer.parseInt(user.getRole()));
                return ps;
            }
        }, holder);

        Long key=holder.getKey().longValue();

        user.setId(key.toString());

        return user  ;
    }

}
