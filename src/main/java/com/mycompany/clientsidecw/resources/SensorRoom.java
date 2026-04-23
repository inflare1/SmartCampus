package com.mycompany.clientsidecw.resources;

import com.mycompany.clientsidecw.dao.GenericDAO;
import com.mycompany.clientsidecw.dao.SmartCampusDatabase;
import com.mycompany.clientsidecw.model.Room;
import com.mycompany.clientsidecw.exceptions.RoomNotEmptyException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import javax.ws.rs.core.Response;

@Path("/rooms")
public class SensorRoom {

    private GenericDAO<Room> roomDAO = new GenericDAO<>(SmartCampusDatabase.ROOMS);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Room> getAllRooms() {
        return roomDAO.getAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRoom(Room room) {
        roomDAO.add(room);
        return Response.status(Response.Status.CREATED)
                .entity(room)
                .build();
    }

    @GET
    @Path("/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Room getRoomById(@PathParam("roomId") String roomId) {
        return roomDAO.getById(roomId);
    }

    @DELETE
    @Path("/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteRoom(@PathParam("roomId") String roomId) {
        Room room = roomDAO.getById(roomId);

        if (room != null && !room.getSensorIds().isEmpty()) {
            throw new RoomNotEmptyException("Room still has sensors assigned");
        } else {
            roomDAO.delete(roomId);
        }

    }
}
