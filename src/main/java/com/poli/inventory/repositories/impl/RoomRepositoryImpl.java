package com.poli.inventory.repositories.impl;



import com.poli.inventory.domain.Campus;
import com.poli.inventory.domain.Room;
import com.poli.inventory.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class RoomRepositoryImpl implements RoomRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Room> consulRooms(String idCampus) {
        String sql = "SELECT * FROM roominventory.rooms WHERE idRoom != 0 AND idCampus=?";
        List<Room> rooms = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,idCampus);
        for (Map row : rows) {
            Room newRoom = new Room();
            newRoom.setIdRoom((Integer) row.get("idRoom"));
            newRoom.setName((String) row.get("name"));
            newRoom.setResponsible((String) row.get("responsible"));
            newRoom.setPhoto((byte[]) row.get("photo"));
            Campus campus=new Campus();
            campus.setIdCampus((Integer) row.get("idCampus"));
            campus.setName((String) row.get("nameCampus"));
            campus.setDirection((String) row.get("direction"));
            newRoom.setCampus(campus);
            rooms.add(newRoom);
        }
        return rooms;
    }

    @Override
    public Room createRoom(Room room) {

        if (String.valueOf(room.getIdRoom()).equals("")||String.valueOf(room.getIdRoom()).equals("0")) {

            return create(room);

        } else {
            return update(room);
        }
    }

    private Room create(Room room) {
        KeyHolder holder = new GeneratedKeyHolder();
        String sql = "INSERT INTO roominventory.rooms (name, responsible,photo,idCampus) VALUES (?, ?, ?,?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, room.getName());
                ps.setString(2, room.getResponsible());
                ps.setBinaryStream(3, getInputStreamImage(room.getPhoto()));
                ps.setInt(4,room.getCampus().getIdCampus());
                return ps;
            }
        }, holder);

        Long key = holder.getKey().longValue();
        room.setIdRoom(key.intValue());
        return room;
    }
    private InputStream getInputStreamImage(byte[] image) {
        return new ByteArrayInputStream(image);
    }

    @Override
    public Room checkRoom(String name) {
        String sql = "select * from roominventory.rooms where name = ?";

        List<Room> rooms = jdbcTemplate.query(sql, new Object[]{name}, new BeanPropertyRowMapper(Room.class));
        return rooms.size() > 0 ? rooms.get(0) : null;
    }

    @Override
    public Room update(Room room) {
        jdbcTemplate.update(
                "UPDATE rooms SET name=?, responsible=?, photo=? WHERE idRoom=?",
                room.getName(),room.getResponsible(),room.getPhoto(),room.getIdRoom());
        return room;
    }
}
