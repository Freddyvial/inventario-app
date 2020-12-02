package com.poli.inventory.services.impl;


import com.poli.inventory.domain.Room;
import com.poli.inventory.repositories.RoomRepository;
import com.poli.inventory.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room getRoomByUser(String isUser) {
        return roomRepository.consultRoomByUser(isUser);
    }

    @Override
    public List<Room> getRooms(String idCampus) {
        return roomRepository.consulRooms(idCampus);
    }

    @Override
    public Room createRoom(Room room) throws Exception {
        if (room.getIdRoom() == 0) {
            return roomRepository.createRoom(room);
        } else {
            Room roomExist = roomRepository.checkRoom(room.getName());

            if (roomExist != null) {
                return roomRepository.createRoom(room);
            } else {
                return null;

            }
        }
    }
}
