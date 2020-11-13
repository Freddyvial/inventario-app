package com.poli.inventory.repositories.impl;



import com.poli.inventory.domain.Role;
import com.poli.inventory.domain.User;
import com.poli.inventory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public User upDatePassword(User user) {
        jdbcTemplate.update(
                "UPDATE roominventory.users SET password = ? WHERE id = ?",
                user.getPassWord(), user.getId());
        return user;
    }
    @Override
    public User consultUser(String userName, String password){
        String sql = "SELECT u.*, r.name as roleName FROM roominventory.users as u inner join roominventory.role as r on u.idRole = r.idRole where u.userName=? and u.password = ?";
        List<User> users=new ArrayList<>();
        List<Map<String, Object>> rows= jdbcTemplate.queryForList(sql ,new Object[] { userName ,password});
        for(Map row:rows){
            User newUser = new User();
            newUser.setId((int)row.get("idRole"));
            newUser.setUserName((String) row.get("userName"));
            newUser.setPassWord((String) row.get("password"));
            Role role=new Role();
            role.setIdRole((String.format(row.get("idRole").toString())));
            role.setName((String) row.get("roleName"));
            newUser.setRole(role);
            users.add(newUser);
        }
         return users.size()>0 ? users.get(0):null;
    }
    public User createUser(User user) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql= "INSERT INTO roominventory.users (userName,passWord,idRole) values(?,?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getUserName());
                ps.setString(2, user.getPassWord());
                ps.setInt(3, Integer.parseInt(user.getRole().getIdRole()));
                return ps;
            }
        }, holder);

        Long key=holder.getKey().longValue();

        user.setId(key.intValue());

        return user  ;
    }

}
