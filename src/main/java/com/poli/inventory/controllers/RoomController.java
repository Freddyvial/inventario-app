package com.poli.inventory.controllers;

import com.poli.inventory.domain.Room;
import com.poli.inventory.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/rooms")
    public List<Room> getArticles(String idCampus){
        return roomService.getRooms(idCampus);
    }

    @PostMapping(path = "/rooms", consumes = "application/json", produces = "application/json")
    public Room setRooms(@RequestBody Room room) throws Exception {

        return roomService.createRoom(room);

    }
}
