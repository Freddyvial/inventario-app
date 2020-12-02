package com.poli.inventory.services;

import com.poli.inventory.domain.Room;

import java.util.List;

public interface RoomService {
    Room getRoomByUser(String isUser);
    List<Room> getRooms(String idCampus);
    Room createRoom(Room room) throws Exception;
}
