package com.mycompany.clientsidecw.resources;

import com.mycompany.clientsidecw.dao.GenericDAO;
import com.mycompany.clientsidecw.dao.SmartCampusDatabase;
import com.mycompany.clientsidecw.exceptions.LinkedResourceNotFoundException;
import com.mycompany.clientsidecw.model.Room;
import com.mycompany.clientsidecw.model.Sensor;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import javax.ws.rs.core.Response;

@Path("/sensors")
public class SensorResource {

    private GenericDAO<Sensor> sensorDAO = new GenericDAO<>(SmartCampusDatabase.SENSORS);
    private GenericDAO<Room> roomDAO = new GenericDAO<>(SmartCampusDatabase.ROOMS);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSensor(Sensor sensor) {
        Room room = roomDAO.getById(sensor.getRoomId());
        if (room == null) {
            throw new LinkedResourceNotFoundException("Room does not exist");
        } else {
            sensorDAO.add(sensor);
            return Response.status(Response.Status.CREATED)
                    .entity(sensor)
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sensor> getSensors(@QueryParam("type") String type) {
        if (type == null) {
            return sensorDAO.getAll();
        } else {
            List<Sensor> filtered = new ArrayList<>();
            for (Sensor sensor : sensorDAO.getAll()) {
                if (sensor.getType().equals(type)) {
                    filtered.add(sensor);
                }
            }
            return filtered;
        }

    }

    @Path("/{sensorId}/readings")
    public SensorReadingResource getSensorReadings(@PathParam("sensorId") String sensorId) {
        return new SensorReadingResource(sensorId);
    }

}
