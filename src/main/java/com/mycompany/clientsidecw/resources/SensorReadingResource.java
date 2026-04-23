    package com.mycompany.clientsidecw.resources;

    import com.mycompany.clientsidecw.dao.GenericDAO;
    import com.mycompany.clientsidecw.dao.SmartCampusDatabase;
    import com.mycompany.clientsidecw.exceptions.SensorUnavailableException;
    import com.mycompany.clientsidecw.model.Sensor;
    import com.mycompany.clientsidecw.model.SensorReading;
    import java.util.*;
    import javax.ws.rs.*;
    import javax.ws.rs.core.*;

 
    public class SensorReadingResource {

        private String sensorId;
        private static Map<String, List<SensorReading>> readings = new HashMap<>();
        private GenericDAO<Sensor> sensorDAO = new GenericDAO<>(SmartCampusDatabase.SENSORS);

        public SensorReadingResource(String sensorId) {
            this.sensorId = sensorId;
        }

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<SensorReading> getReadingHistory() {
            List<SensorReading> sensorReadings = readings.get(sensorId);

            if (sensorReadings == null) {
                return new ArrayList<>();
            }

            return sensorReadings;
        }

        @POST
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response addReading(SensorReading reading) {
            Sensor sensor = sensorDAO.getById(sensorId);

            if (sensor.getStatus().equals("MAINTENANCE")) {
                throw new SensorUnavailableException("This sensor cannot accept new readings");
            }

            List<SensorReading> sensorReadings = readings.get(sensorId);

            if (sensorReadings == null) {
                sensorReadings = new ArrayList<>();
                readings.put(sensorId, sensorReadings);
            }

            sensorReadings.add(reading);
            sensor.setCurrentValue(reading.getValue());

            return Response.status(Response.Status.CREATED)
                    .entity(reading)
                    .build();
        }
    }
