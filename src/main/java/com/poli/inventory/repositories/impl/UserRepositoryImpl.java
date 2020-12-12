package com.poli.inventory.repositories.impl;



import com.poli.inventory.domain.Campus;
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
                "UPDATE u280625412_inventory.users SET password = ? WHERE idUser = ?",
                user.getPassword(), user.getIdUser());
        return user;
    }
    @Override
    public  List<User> consultUserByCampus(String idCampus){
        String sql = "SELECT u.idUser,u.userName FROM u280625412_inventory.users as u\n" +
                "where idCampus=?";
        List<User> users=new ArrayList<>();
        List<Map<String, Object>> rows= jdbcTemplate.queryForList(sql,new Object[] { idCampus});
        for(Map row:rows){
        User newUser = new User();
        newUser.setIdUser((int) row.get("idUser"));
        newUser.setUserName((String) row.get("userName"));
        users.add(newUser);
        }
        return users;
    }
    @Override
    public User consultUser(String userName, String password){
        if(password==null){
            String sql = "SELECT u.*, r.name as roleName FROM u280625412_inventory.users as u inner join u280625412_inventory.role as r on u.idRole = r.idRole where u.userName=?";
            List<Map<String, Object>> rows= jdbcTemplate.queryForList(sql ,new Object[] { userName});
            List<User> users=new ArrayList<>();
            for(Map row:rows){
                User newUser = new User();
                newUser.setIdUser((int)row.get("idUser"));
                newUser.setUserName((String) row.get("userName"));
                Role role=new Role();
                role.setIdRole((String.format(row.get("idRole").toString())));
                role.setName((String) row.get("roleName"));
                newUser.setRole(role);
                Campus campus= new Campus();
                campus.setIdCampus((int) row.get("idCampus"));
                newUser.setCampus(campus);
                users.add(newUser);
            }
            return users.size()>0 ? users.get(0):null;
        }else{
            String sql = "SELECT u.*, r.name as roleName FROM u280625412_inventory.users as u inner join u280625412_inventory.role as r on u.idRole = r.idRole where u.userName=? and u.password = ?";
             List<Map<String, Object>> rows= jdbcTemplate.queryForList(sql ,new Object[] { userName ,password});
            List<User> users=new ArrayList<>();
            for(Map row:rows){
                User newUser = new User();
                newUser.setIdUser((int)row.get("idUser"));
                newUser.setUserName((String) row.get("userName"));
                Role role=new Role();
                role.setIdRole((String.format(row.get("idRole").toString())));
                role.setName((String) row.get("roleName"));
                newUser.setRole(role);
                Campus campus= new Campus();
                campus.setIdCampus((int) row.get("idCampus"));
                newUser.setCampus(campus);
                users.add(newUser);
            }
            return users.size()>0 ? users.get(0):null;
        }

    }
    public User createUser(User user) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql= "INSERT INTO u280625412_inventory.users (userName,passWord,idRole,idCampus) values(?,?,?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getUserName());
                ps.setString(2, user.getPassword());
                ps.setInt(3, Integer.parseInt(user.getRole().getIdRole()));
                ps.setInt(4, user.getCampus().getIdCampus());
                return ps;
            }
        }, holder);

        Long key=holder.getKey().longValue();

        user.setIdUser(key.intValue());

        return user  ;
    }

}
