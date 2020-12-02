package com.poli.inventory.repositories;

import com.poli.inventory.domain.Room;

import java.util.List;

public interface RoomRepository {

    List<Room> consulRooms(String idCampus);
    Room createRoom(Room room);
    Room checkRoom(String name);
    Room update(Room room);
    Room consultRoomByUser(String idUser);

}